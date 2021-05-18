package ru.otus.books.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.books.model.BookCategory;
import ru.otus.books.repository.BookCategoryRepository;
import ru.otus.books.repository.SequenceRepository;
import ru.otus.books.repository.custom.BookCategoryRepositoryCustom;


public class BookCategoryRepositoryImpl implements BookCategoryRepositoryCustom {

    @Autowired
    BookCategoryRepository bookCategoryRepository;
    @Autowired
    SequenceRepository sequenceRepository;
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void insertBookCategoryWithGeneratedId(BookCategory bc) {
        bc.setBookCategoryId(sequenceRepository.getNextSequenceId(BookCategory.COLLECTION_NAME));
        bookCategoryRepository.save(bc);
    }

}
