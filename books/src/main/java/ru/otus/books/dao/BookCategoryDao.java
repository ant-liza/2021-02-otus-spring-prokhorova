package ru.otus.books.dao;

import ru.otus.books.domain.BookCategory;

import java.util.List;

public interface BookCategoryDao {
    int count();

    long insert(BookCategory bookCategory);// create

    List<BookCategory> getAll();//read

    BookCategory getById(long bookCategoryId);//read

    void updateBookCategoryNameById(long bookCategoryId, String value);//update

    void deleteById(long bookCategoryId);//delete
}
