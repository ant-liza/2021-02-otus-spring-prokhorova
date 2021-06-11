package ru.otus.books.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.books.model.Author;
import ru.otus.books.repository.custom.AuthorRepositoryCustom;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String>, AuthorRepositoryCustom {

    Optional<Author> findByFirstName(String firstName);
    List<Author> findAllByOrderByFirstNameAsc();

}
