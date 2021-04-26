package ru.otus.books.dao;

import ru.otus.books.domain.Author;

import java.util.List;

public interface AuthorDao {
    int count();

    long insert(Author author);// create

    List<Author> getAllAuthors();//read

    Author getById(long authorId);//read

    void updateFirstNameById(long authorId, String newValue);//update

    void deleteById(long authorId);//delete

    long getMaxId();

    List<Author> findAllAuthorsWithBooks() ;

}
