package com.marmuz.services;

import com.marmuz.models.Book;
import com.marmuz.models.Person;
import com.marmuz.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;


    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }


    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findPersonBy(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    public Optional<Person> findPersonBy(String fullName) {
        return peopleRepository.findByFullName(fullName);

    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    //метод получения книг по человеку и проверка на просроченность владения этими книгами
    public List<Book> findBookByPersonId(int personId) {
        Optional<Person> foundPerson = peopleRepository.findById(personId);

        if (foundPerson.isPresent()) {
            Hibernate.initialize(foundPerson.get().getBook()); //подгружаем на всякий случай, если в дальнейшем удалится итерация по книгам

            foundPerson.get().getBook().forEach(book -> {
                long millies = Math.abs(book.getTakenAt().getTime() - new Date().getTime());
                //вычисленное время больше 10 суток->...
                if (millies > 864_000_000) {
                    //книга просрочена
                    book.setExpired(true);
                }
            });
            return foundPerson.get().getBook();
        }else{
            return Collections.emptyList();
        }

    }
}
