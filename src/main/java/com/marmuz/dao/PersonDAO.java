package com.marmuz.dao;


import org.springframework.stereotype.Component;



@Component
public class PersonDAO {
    /*private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM Person",new BeanPropertyRowMapper<>(Person.class));
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person (full_name,year_of_birth) VALUES(?,?)"
                ,person.getFullName(),person.getYearOfBirth());
    }

    public Person findPerson(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id},new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public Optional<Person> findPerson(String fullName){
        return jdbcTemplate.query("SELECT * FROM Person WHERE fullName=?", new Object[]{fullName}
                ,new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void update(int id,Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET full_name=?, year_of_birth=? WHERE id=?",
                updatedPerson.getFullName(), updatedPerson.getYearOfBirth(), id);

    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?",id);
    }

    public List<Book> getAllBookByPersonID(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?"
                , new Object[]{id},new BeanPropertyRowMapper<>(Book.class));
    }*/

}
