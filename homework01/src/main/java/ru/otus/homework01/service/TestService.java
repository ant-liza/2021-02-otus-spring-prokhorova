package ru.otus.homework01.service;

import ru.otus.homework01.domain.ExamTest;

import java.io.IOException;

public interface TestService {
    /*
    Метод для загрузки теста
    Возвращает объект Тест с заполненными вопросами, ответами и т.д.
     */
    ExamTest loadTest() throws IOException;

}
