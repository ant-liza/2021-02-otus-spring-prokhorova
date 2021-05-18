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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName(" Сервис авторов должен: ")
@DataMongoTest
public class AuthorRepositoryTest {
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;

    private final static Long AUTHOR_GENERATED_ID = 4L;

    @DisplayName(" корректно добавлять автора со сгенерированным ИД")
    @Test
    void shouldInsertAuthorWithGeneratedId() {
        Author testAuthor = new Author("test","test","");
        authorRepository.insertAuthorWithGeneratedId(testAuthor);
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(AUTHOR_GENERATED_ID));
        Author existsAuthor = mongoTemplate.findOne(query, Author.class);
        assertThat(testAuthor.getAuthorId()).isEqualTo(existsAuthor.getAuthorId());
    }

    @DisplayName(" добавлять книгу к автору")
    @Test
    void shouldAddBookToAuthor() {
        Author author = new Author("test","test","");
        authorRepository.insertAuthorWithGeneratedId(author);
        Book book = new Book("TEST");
        bookRepository.insertBookWithGeneratedId(book);

        authorRepository.addBookToAuthor(author.getAuthorId(), book.getBookId());
        List<Book> bookList = authorRepository.findById(AUTHOR_GENERATED_ID).get().getBooks();
        assertThat(bookList).isNotNull().hasSize(1)
                .allMatch(b->b.getBookId().equals(book.getBookId()))
                .allMatch(b->b.getTitle().equalsIgnoreCase(book.getTitle()));
    }

}
