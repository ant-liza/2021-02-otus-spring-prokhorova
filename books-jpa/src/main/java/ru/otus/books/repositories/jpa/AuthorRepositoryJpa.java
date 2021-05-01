package ru.otus.books.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.books.models.Author;

import java.util.Optional;

public interface AuthorRepositoryJpa extends JpaRepository<Author, Long> {
    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);
}
