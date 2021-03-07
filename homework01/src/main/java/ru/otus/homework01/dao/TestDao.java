package ru.otus.homework01.dao;

import ru.otus.homework01.domain.ExamTest;

import java.io.BufferedReader;
import java.io.IOException;

public interface TestDao {

    /**
     * Получение BufferedReader из источника для дальнейшего его чтения
     * @return
     */
    BufferedReader getReaderFromDefaultSource();

    /**
     * Метод принимает BufferedReader в качестве источника(поток) и читает его, заполняя все поля для
     * объекта ExamTest
     * @return ExamTest - заполненный из источника тест с вопросами и ответами
     * @throws IOException
     */
    ExamTest getTest(BufferedReader reader) throws IOException;


}
