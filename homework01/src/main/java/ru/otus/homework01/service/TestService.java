package ru.otus.homework01.service;

import ru.otus.homework01.domain.ExamTest;
import ru.otus.homework01.domain.TestResult;

import java.io.IOException;

public interface TestService {

    /**
     * Метод для загрузки теста
     * @return Возвращает объект ExamTest с заполненными вопросами, ответами и т.д.
     * @throws IOException
     */
    ExamTest getTest() throws IOException;

    void startTest(TestResult testResult) throws IOException;

}
