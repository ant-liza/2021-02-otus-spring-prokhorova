package ru.otus.homework01.service;

import ru.otus.homework01.dao.TestDao;
import ru.otus.homework01.domain.ExamTest;

import java.io.BufferedReader;
import java.io.IOException;

public class TestServiceImpl implements TestService {
    private final TestDao dao;

    public TestServiceImpl(TestDao dao) {
        this.dao = dao;
    }

    @Override
    public ExamTest loadTest() throws IOException {
        BufferedReader reader = dao.getReaderFromDefaultSource();
        return dao.getTest(reader);
    }


}
