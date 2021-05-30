package ru.otus.books.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.books.model.Comment;
import ru.otus.books.repository.custom.CommentRepositoryCustom;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, Long>, CommentRepositoryCustom {
    List<Comment> findByBook(long bookID);
}
