package ru.otus.books.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {
    private long bookId;
    private Author author;
    private List<BookCategory> bookCategory;
    private String title;
}
