package com.marmuz.dao;

import org.springframework.stereotype.Component;

@Component
public class BookDAO {
    /*private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Book> getAllBook() {
        return jdbcTemplate.query("SELECT * FROM Book",new BeanPropertyRowMapper<>(Book.class));
    }

    public void create(Book book) {
        jdbcTemplate.update("INSERT INTO Book (title,author,year) VALUES (?,?,?)"
                , book.getTitle(), book.getAuthor(), book.getYear());
    }

    public Book findBookById(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id =?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void updateBook(Book book, int id) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year=? WHERE id=?"
                , book.getTitle(), book.getAuthor(), book.getYear(), id);
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }


    public Optional<Person> getPersonOwner(int bookId) {
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.person_id = Person.id WHERE Book.id = ?",
                new Object[]{bookId}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE Book SET person_id= NULL WHERE id = ?",id);
    }
    public void assign(int id,Person person) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id = ?",person.getId(),id);
    }*/
}
