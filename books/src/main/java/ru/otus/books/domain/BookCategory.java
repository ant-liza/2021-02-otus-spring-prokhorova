package ru.otus.books.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class BookCategory {
private  final long bookCategoryId;
private final String bookCategoryName;
}
