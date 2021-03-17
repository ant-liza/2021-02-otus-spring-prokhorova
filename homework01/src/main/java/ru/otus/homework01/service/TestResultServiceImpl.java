package ru.otus.homework01.service;

import org.springframework.stereotype.Service;
import ru.otus.homework01.dao.TestResultDao;
import ru.otus.homework01.domain.ExamTest;
import ru.otus.homework01.domain.Question;
import ru.otus.homework01.domain.StudentAnswer;
import ru.otus.homework01.domain.TestResult;

import java.util.List;

@Service
public class TestResultServiceImpl implements TestResultService {
    private final TestResultDao dao;

    public TestResultServiceImpl(TestResultDao dao) {
        this.dao = dao;
    }

    @Override
    public TestResult getEmptyTestResult() {
        return dao.getNewTestResult();
    }

    @Override
    public void showResults(TestResult testResult) {
        System.out.println("Результаты теста:");
        System.out.println("Имя и фамилия: " + testResult.getStudentInfo());
        System.out.println("Всего вопросов/Количество правильных ответов : " +
                testResult.getNumberOfQuestions()
                + "/" + testResult.getNumberOfCorrectAnswers());
        System.out.println("Оценка : " + calculateMark(testResult));

    }

    @Override
    public int calculateMark(TestResult testResult) {
        int numberOfCorrectAnswers = testResult.getNumberOfCorrectAnswers();
        int mark;
        switch (numberOfCorrectAnswers) {
            case 5:
                mark = 5;
                break;
            case 4:
                mark = 4;
                break;
            case 3:
                mark = 3;
                break;
            default:
                mark = 2;
                break;
        }
        return mark;
    }
    public int getStudentCorrectAnswers(ExamTest exam, List<StudentAnswer> studentAnswers) {
        int numberOfCorrectAnswers = 0;
        for (Question question : exam.getListOfQuestions()) {
            for (StudentAnswer studentAnswer : studentAnswers) {
                if (question.equals(studentAnswer.getQuestion())) {
                    if (question.getNumOfCorrectAnswer() == studentAnswer.getStudentAnswer()) {
                        numberOfCorrectAnswers++;
                    }
                }
            }
        }
        return numberOfCorrectAnswers;
    }
}
