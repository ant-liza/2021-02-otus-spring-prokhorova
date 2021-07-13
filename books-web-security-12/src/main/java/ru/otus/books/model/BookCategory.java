package ru.otus.books.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = BookCategory.COLLECTION_NAME)
public class BookCategory {
    public static final String COLLECTION_NAME = "book_categories";

    @Id
    private String bookCategoryId;

    @Field(name = "BOOK_CATEGORY_NAME")
    private String bookCategoryName;

    public BookCategory() {
    }

    public BookCategory(String bookCategoryName) {
        this.bookCategoryName = bookCategoryName;
    }

    public String getBookCategoryId() {
        return bookCategoryId;
    }

    public void setBookCategoryId(String bookCategoryId) {
        this.bookCategoryId = bookCategoryId;
    }

    public String getBookCategoryName() {
        return bookCategoryName;
    }

    public void setBookCategoryName(String bookCategoryName) {
        this.bookCategoryName = bookCategoryName;
    }
}
