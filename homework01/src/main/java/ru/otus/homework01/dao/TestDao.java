package ru.otus.homework01.dao;

import ru.otus.homework01.domain.ExamTest;

import java.io.IOException;

public interface TestDao {
    /**
     * Метод для получения теста(вопросы, ответы и т.д.) из предопределенного источника/ресурса
     * @return ExamTest - заполненный тест из источника(ресурса)
     * @throws IOException
     */

    ExamTest getTestFromDefaultSource() throws IOException;
}
