package ru.otus.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.books.domain.Book;
import ru.otus.books.repository.custom.jpa.BookRepositoryCustom;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {
    List<Book> findByTitle(String title);

    List<Book> findByTitleContaining(String value);

}
