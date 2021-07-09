package ru.otus.books.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.books.model.UserRoles;

import java.util.List;

public interface UserRolesRepository extends MongoRepository<UserRoles, String> {

    List<UserRoles> findByUserName(String value);
}
