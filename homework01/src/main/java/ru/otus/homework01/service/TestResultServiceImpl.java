package ru.otus.homework01.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.homework01.bean.CustomLocaleEnvelope;
import ru.otus.homework01.dao.TestResultDao;
import ru.otus.homework01.domain.ExamTest;
import ru.otus.homework01.domain.Question;
import ru.otus.homework01.domain.StudentAnswer;
import ru.otus.homework01.domain.TestResult;

import java.util.List;

@Service
public class TestResultServiceImpl implements TestResultService {
    private final TestResultDao dao;
    private final CustomLocaleEnvelope customLocaleEnvelope;
    private final MessageSource messageSource;

    public TestResultServiceImpl(TestResultDao dao,
                                 CustomLocaleEnvelope customLocaleEnvelope,
                                 MessageSource messageSource) {
        this.customLocaleEnvelope = customLocaleEnvelope;
        this.dao = dao;
        this.messageSource = messageSource;
    }

    @Override
    public TestResult getEmptyTestResult() {
        return dao.getNewTestResult();
    }

    @Override
    public void showResults(TestResult testResult) {
        System.out.println(messageSource.getMessage("test.results",null,
                customLocaleEnvelope.getDefaultLocale()));
        System.out.println(messageSource.getMessage("test.full-name",new String[]{
                        testResult.getStudent().getFirstName(),
                        testResult.getStudent().getLastName()},
                customLocaleEnvelope.getDefaultLocale()));
        System.out.println(messageSource.getMessage("test.count-questions-and-answers",
                new String[]{Integer.toString(testResult.getNumberOfQuestions()),
                        Integer.toString(testResult.getNumberOfCorrectAnswers())},
                customLocaleEnvelope.getDefaultLocale()));
        System.out.println(messageSource.getMessage("test.mark",
                new String[]{Integer.toString(calculateMark(testResult))},
                customLocaleEnvelope.getDefaultLocale()));

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
