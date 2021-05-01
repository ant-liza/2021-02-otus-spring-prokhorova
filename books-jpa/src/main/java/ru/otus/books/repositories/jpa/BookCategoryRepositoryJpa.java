package ru.otus.books.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.books.models.BookCategory;

import java.util.Optional;

public interface BookCategoryRepositoryJpa extends JpaRepository<BookCategory, Long> {

    Optional<BookCategory> findByBookCategoryName(String value);

}
