package ru.otus.homework01.service;

import ru.otus.homework01.domain.Question;

public interface ReaderService {
    void showCustomRow(String value);
    String readRowStr();
    int readRowInt();

    /**
     * Отображает вопрос с вариантами ответами из объекта Question
     * @param value - объект-вопрос
     */
    void showQuestionRow(Question value);
}
