package ru.otus.homework01.service;

import ru.otus.homework01.domain.ExamTest;

import java.io.IOException;

public interface TestService {

    /**
     * Метод для загрузки теста
     * @return Возвращает объект ExamTest с заполненными вопросами, ответами и т.д.
     * @throws IOException
     */
    ExamTest loadTest() throws IOException;

}
