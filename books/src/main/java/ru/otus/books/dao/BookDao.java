package ru.otus.books.dao;

import ru.otus.books.domain.Book;

import java.util.List;

public interface BookDao {
    int count();

    long insert(Book book);// create

    List<Book> getAll();//read

    Book getById(long bookId);//read

    void updateBookDescriptionById(long bookId, String newValue);//update

    void deleteById(long bookId);//delete

}
