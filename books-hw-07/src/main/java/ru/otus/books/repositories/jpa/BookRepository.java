package ru.otus.books.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.books.models.Book;
import ru.otus.books.repositories.jpa.custom.BookRepositoryCustom;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long>,
        BookRepositoryCustom {


    Optional<Book> findByTitle(String title);

    List<Book> findByTitleContaining(String value);
}
