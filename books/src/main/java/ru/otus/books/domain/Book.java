package ru.otus.books.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Book {
    private final long bookId;
    private final int authorId;
    private final int bookCategoryId;
    private final String description;
}
