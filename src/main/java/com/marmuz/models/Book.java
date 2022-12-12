package com.marmuz.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "Название книги не должно быть пустым")
@Column(name = "title")
    private String title;
    @NotEmpty(message = "Поле 'автор' не должно быть пустым")
    @Column(name = "author")
    private String author;
    @Size(min = 1800, max = 2022,message ="Год выпуска книги должен быть между 1800 и 2022")
    @Column(name = "year")
    private int year;

    @ManyToOne
    @JoinColumn(name = "person_id",referencedColumnName = "id")
    private Person person;

    public Book() {
    }

    public Book(int id, String title, String author, int year, Person person) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", person=" + person +
                '}';
    }
}
