package com.marmuz.services;

import com.marmuz.models.Book;
import com.marmuz.models.Person;
import com.marmuz.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;
    private final PeopleService peopleService;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleService peopleService) {
        this.booksRepository = booksRepository;
        this.peopleService = peopleService;
    }

    public List<Book> findAll(boolean sortByYear) {
        if (sortByYear) {
            return booksRepository.findAll(Sort.by("year"));
        } else {
            return booksRepository.findAll();
        }
    }

    public List<Book> findWithPagination(Integer page, Integer bookPage, boolean sortByYear) {
        if (sortByYear)
            return booksRepository.findAll(PageRequest.of(page, bookPage, Sort.by("year"))).getContent();
        else
            return booksRepository.findAll(PageRequest.of(page, bookPage)).getContent();
    }

    public Book findBookById(int id) {
        Optional<Book> foundBook = booksRepository.findById(id);
        return foundBook.orElse(null);
    }

    //Для поиска книга по названию или нескольким буквам
    public List<Book> searchByTitle(String startingWith) {
        return booksRepository.findByTitleStartingWith(startingWith);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void updateBook(int id, Book updatedBook) {
        Book needToUpdate = booksRepository.findById(id).get();
        updatedBook.setId(id);
        updatedBook.setPerson(needToUpdate.getPerson());
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void deleteBook(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void releaseBook(int id) {
        booksRepository.findById(id).ifPresent(book -> {
            book.setPerson(null);
            book.setTakenAt(null);
        });
    }

    @Transactional
    public void assignBook(int id, Person person) {
        booksRepository.findById(id).ifPresent(book -> {
            book.setPerson(person);
            book.setTakenAt(new Date());
        });
    }

    public Person getBookOwner(int id) {
        return booksRepository.findById(id).map(Book::getPerson).orElse(null);
    }
}
