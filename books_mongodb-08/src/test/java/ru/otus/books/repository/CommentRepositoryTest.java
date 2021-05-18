package ru.otus.books.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.otus.books.model.Author;
import ru.otus.books.model.Book;
import ru.otus.books.model.Comment;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName(" Сервис комментариев должен: ")
@DataMongoTest
public class CommentRepositoryTest {
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    BookRepository bookRepository;
    private final static Long COMMENT_GENERATED_ID = 4L;

    @DisplayName(" корректно добавлять комментарий со сгенерированным ИД")
    @Test
    void shouldInsertAuthorWithGeneratedId() {
        Book b = new  Book("TEST");
        bookRepository.insertBookWithGeneratedId(b);
        Comment testComment = new Comment("test", b);
        commentRepository.insertCommentWithGeneratedId(testComment);
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(COMMENT_GENERATED_ID));
        Comment existsComment = mongoTemplate.findOne(query, Comment.class);
        assertThat(testComment.getNoteId()).isEqualTo(existsComment.getNoteId());
    }
}
