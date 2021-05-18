package ru.otus.books.repository.custom;

import ru.otus.books.model.Book;

public interface BookRepositoryCustom {
    /**
     * Добавление книги со сгенерированным ИД типа LONG
     * @param book - Объект книга
     */
    void insertBookWithGeneratedId(Book book);
}
