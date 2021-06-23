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

    @Id
    private String bookId;

    @DBRef
    private List<BookCategory> bookCategory;

    @DBRef
    private List<Comment> notes;

    @DBRef
    private Author author;

    @Field(name = "TITLE")
    private String title;

    public Book(String title) {
        this.title = title;
    }

    public Book(String title, List<BookCategory> bookCategory, Author author) {
        this.bookCategory = bookCategory;
        this.title = title;
        this.author = author;
    }

    public Book(List<BookCategory> bookCategory, List<Comment> notes, String title) {
        this.bookCategory = bookCategory;
        this.notes = notes;
        this.title = title;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", bookCategory=" + bookCategory +
                ", notes=" + notes +
                ", author=" + author.getFirstAndLastAuthorName() +
                ", title='" + title + '\'' +
                '}';
    }
}
