package ru.otus.books.repository.impl;

import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.otus.books.model.Sequence;
import ru.otus.books.repository.custom.SequenceRepositoryCustom;

public class SequenceRepositoryImpl implements SequenceRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Long getNextSequenceId(String key) {
        Update update = new Update();
        update.inc("sequence", 1);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        Query query = new Query(Criteria.where("id").is(key));
        Sequence sequence = mongoTemplate.findAndModify(query, update, options, Sequence.class);
        if (sequence == null) {
            throw new IllegalArgumentException("Unable to get sequence for key: " + key);
        }
        return sequence.getSequence();
    }
}
