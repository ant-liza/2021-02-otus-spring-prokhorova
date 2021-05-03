package ru.otus.books.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.books.models.Book;
import ru.otus.books.repositories.jpa.custom.BookRepositoryCustom;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long>,
        BookRepositoryCustom {

    @Query("select b from Author a join a.books b where a.authorId=:id")
    List<Book> getAllBooksByAuthor(@Param("id") long authorId);

    Optional<Book> findByTitle(String title);

    List<Book> findByTitleContaining(String value);
}
