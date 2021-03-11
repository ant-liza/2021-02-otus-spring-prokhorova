package ru.otus.homework01.dao;

import ru.otus.homework01.domain.ExamTest;

import java.io.IOException;

public interface TestDao {
    ExamTest getTest() throws IOException;

}
