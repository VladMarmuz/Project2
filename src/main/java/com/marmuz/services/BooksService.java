package com.marmuz.services;

import com.marmuz.models.Book;
import com.marmuz.models.Person;
import com.marmuz.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Book> findBooksByPersonId(int personId){
        return booksRepository.findByPersonId(personId);
    }

    public List<Book> findAll(){
        return booksRepository.findAll(Sort.by("year"));
    }

    public Book findBookById(int id){
        Optional<Book> foundBook = booksRepository.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Book book){
        booksRepository.save(book);
    }

    @Transactional
    public void updateBook(int id, Book updatedBook){
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void deleteBook(int id){
        booksRepository.deleteById(id);
    }

    public Person findPersonByBookId(int bookId){
       return peopleService.findPersonByBookId(bookId);
    }

    @Transactional
    public void releaseBook(int id){
        Book foundBook = booksRepository.findById(id).get();
        foundBook.setPerson(null);
        booksRepository.save(foundBook);
    }

    @Transactional
    public void assignBook(int id, Person person){
        Book foundBook = booksRepository.findById(id).get();
        foundBook.setPerson(person);
        booksRepository.save(foundBook);
    }


}
