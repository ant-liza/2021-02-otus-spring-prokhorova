package ru.otus.books.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookCategory {
    private long bookCategoryId;
    private String bookCategoryName;
}
