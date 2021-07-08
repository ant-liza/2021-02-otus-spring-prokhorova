package ru.otus.books.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.books.model.UserMongo;

public interface UserRepository extends MongoRepository<UserMongo, String> {
     UserMongo findByUserName(String  value);
}
