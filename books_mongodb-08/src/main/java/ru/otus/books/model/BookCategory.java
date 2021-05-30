package ru.otus.books.model;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@NoArgsConstructor
@Document(collection = BookCategory.COLLECTION_NAME)
public class BookCategory {
    public static final String COLLECTION_NAME = "book_categories";

    public BookCategory(String bookCategoryName) {
        this.bookCategoryName = bookCategoryName;
    }

    @Id
    private Long bookCategoryId;

    @Field(name = "BOOK_CATEGORY_NAME")
    private String bookCategoryName;

    public Long getBookCategoryId() {
        return bookCategoryId;
    }

    public void setBookCategoryId(Long bookCategoryId) {
        this.bookCategoryId = bookCategoryId;
    }

    public String getBookCategoryName() {
        return bookCategoryName;
    }

    public void setBookCategoryName(String bookCategoryName) {
        this.bookCategoryName = bookCategoryName;
    }
}
