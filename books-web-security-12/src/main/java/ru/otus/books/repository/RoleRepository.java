package ru.otus.books.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.books.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
}
