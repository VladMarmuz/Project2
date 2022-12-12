package com.marmuz.services;

import com.marmuz.models.Book;
import com.marmuz.models.Person;
import com.marmuz.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    private final BooksService booksService;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, BooksService booksService) {
        this.peopleRepository = peopleRepository;
        this.booksService = booksService;
    }


    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public Person findPersonBy(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    public Optional<Person> findPersonBy(String fullName){
        return peopleRepository.findByFullName(fullName);

    }

    @Transactional
    public void save(Person person){
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson){
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }

    public List<Book> findBookByPersonId(int personId){
        return booksService.findBooksByPersonId(personId);
    }

    public Person findPersonByBookId(int bookId){
        Optional<Person> foundPerson = peopleRepository.findByBookId(bookId);
        return foundPerson.orElse(null);
    }
}
