package ru.otus.books.repositories.relations;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BookCategoryToBookRelation {
    private final long bookId;
    private final long bookCategoryId;

}
