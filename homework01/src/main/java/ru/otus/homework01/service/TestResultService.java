package ru.otus.homework01.service;

import ru.otus.homework01.domain.TestResult;

public interface TestResultService {

    void showResults(TestResult testResult);

    TestResult getEmptyTestResult();
}
