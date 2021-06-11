package ru.otus.books.repository.custom;

public interface AuthorRepositoryCustom {

    /**
     * Добавление книги к автору
     * @param authorId - ИД автора
     * @param bookId - ИД книги
     */
    void addBookToAuthor(String authorId, String bookId);
}
