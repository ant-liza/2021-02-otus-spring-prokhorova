package ru.otus.books.repository.custom;

import ru.otus.books.model.Author;

public interface AuthorRepositoryCustom {
    /**
     * Добавление автора со сгенерированным ИД типа LONG
     * @param author - Объект автор
     */
    void insertAuthorWithGeneratedId(Author author);

    /**
     * Добавление книги к автору
     * @param authorId - ИД автора
     * @param bookId - ИД книги
     */
    void addBookToAuthor(Long authorId, Long bookId);
}
