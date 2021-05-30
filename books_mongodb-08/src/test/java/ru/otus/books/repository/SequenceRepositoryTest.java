package ru.otus.books.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class SequenceRepositoryTest {
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    SequenceRepository sequenceRepository;
    private static final String AUTHOR_COLLECTION_NAME= "authors";
    private static final String BOOK_COLLECTION_NAME= "books";
    private static final Long NEXT_SEQUENCE= 4L;
    private static final Long NEXT_BOOK_SEQUENCE= 9L;

    @Test
    void shouldReturnCorrectSequence(){
        long seq = sequenceRepository.getNextSequenceId(AUTHOR_COLLECTION_NAME);
        assertThat(seq).isEqualTo(NEXT_SEQUENCE);

        long seqBooks = sequenceRepository.getNextSequenceId(BOOK_COLLECTION_NAME);
        assertThat(seqBooks).isEqualTo(NEXT_BOOK_SEQUENCE);
    }
}
