package ru.otus.books.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.books.model.Sequence;
import ru.otus.books.repository.custom.SequenceRepositoryCustom;

public interface SequenceRepository extends MongoRepository<Sequence,String>, SequenceRepositoryCustom {
}
