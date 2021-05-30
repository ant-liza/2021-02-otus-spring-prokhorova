package ru.otus.books.repository.custom;

public interface SequenceRepositoryCustom {
    Long getNextSequenceId(String key);
}
