package ru.otus.books.model;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Класс для хранения и генерации уникального идентификатора типа LONG для таблиц в MongoDB
 */
@NoArgsConstructor
@Document(collection = Sequence.SEQ_NAME)
public class Sequence {
    public static final String SEQ_NAME = "sequences";
    @Id
    private String id;
    private Long sequence;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    public Sequence(String id, Long sequence) {
        this.id = id;
        this.sequence = sequence;
    }
}
