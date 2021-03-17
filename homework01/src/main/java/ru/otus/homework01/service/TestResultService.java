package ru.otus.homework01.service;

import ru.otus.homework01.domain.ExamTest;
import ru.otus.homework01.domain.StudentAnswer;
import ru.otus.homework01.domain.TestResult;

import java.util.List;

public interface TestResultService {
    /**
     * Метод для отображения результатов тестирования
     *
     * @param testResult - заполненный объект с сохраненными результатами
     *                   теста(ответы студента, оценка и т.д.)
     */
    void showResults(TestResult testResult);

    /**
     * Метод для получения объекта, который будет хранить результаты теста
     *
     * @return - пустой объект для хранения результатов тестирования
     */
    TestResult getEmptyTestResult();

    /**
     * Метод для рассчета итоговой оценки за тест
     *
     * @param testResult - заполненный объект с сохраненными результатами
     *                   теста(ответы студента, оценка и т.д.)
     * @return - рассчитанная оценка за тест
     */
    int calculateMark(TestResult testResult);

    int getStudentCorrectAnswers(ExamTest exam, List<StudentAnswer> studentAnswers);
}
