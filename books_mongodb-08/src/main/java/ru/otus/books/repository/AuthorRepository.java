package ru.otus.books.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.books.model.Author;
import ru.otus.books.repository.custom.AuthorRepositoryCustom;

public interface AuthorRepository extends MongoRepository<Author,Long> , AuthorRepositoryCustom {
}
