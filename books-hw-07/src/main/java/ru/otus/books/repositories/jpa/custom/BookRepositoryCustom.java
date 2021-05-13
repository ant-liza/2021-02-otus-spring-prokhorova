package ru.otus.books.repositories.jpa.custom;

import ru.otus.books.exceptions.BookCategoryNotFoundException;
import ru.otus.books.exceptions.BookNotFoundException;

public interface BookRepositoryCustom {
    void deleteBookWithRelatedRecords(long bookId) throws BookNotFoundException;
    void addBook(String title, long bookCategoryId) throws BookCategoryNotFoundException;

}
