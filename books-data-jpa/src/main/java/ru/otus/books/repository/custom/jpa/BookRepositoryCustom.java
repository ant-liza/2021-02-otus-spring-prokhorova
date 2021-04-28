package ru.otus.books.repository.custom.jpa;

import ru.otus.books.domain.Book;

public interface BookRepositoryCustom {

    void deleteBookWithRelatedRecords(long bookId);
}
