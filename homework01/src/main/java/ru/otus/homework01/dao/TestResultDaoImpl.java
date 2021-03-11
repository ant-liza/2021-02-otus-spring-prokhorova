package ru.otus.homework01.dao;

import org.springframework.stereotype.Component;
import ru.otus.homework01.domain.TestResult;

@Component
public class TestResultDaoImpl implements TestResultDao {


    @Override
    public TestResult getNewTestResult() {
        return new TestResult(null,null);
    }
}
