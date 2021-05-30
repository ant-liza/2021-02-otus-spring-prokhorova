package ru.otus.books.model;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@NoArgsConstructor
@Document(collection = Book.COLLECTION_NAME)
public class Book {
    public static final String COLLECTION_NAME = "books";

    public Book(String title) {
        this.title = title;
    }

    public Book(String title, List<BookCategory> bookCategory) {
        this.bookCategory = bookCategory;
        this.title = title;
    }

    public Book(List<BookCategory> bookCategory, List<Comment> notes, String title) {
        this.bookCategory = bookCategory;
        this.notes = notes;
        this.title = title;
    }

    @Id
    private Long bookId;

    @DBRef
    private List<BookCategory> bookCategory;

    @DBRef
    private List<Comment> notes;

    @Field(name = "TITLE")
    private String title;


    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public List<BookCategory> getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(List<BookCategory> bookCategory) {
        this.bookCategory = bookCategory;
    }

    public List<Comment> getNotes() {
        return notes;
    }

    public void setNotes(List<Comment> notes) {
        this.notes = notes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
