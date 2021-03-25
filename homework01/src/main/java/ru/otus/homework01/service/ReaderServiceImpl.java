package ru.otus.homework01.service;

import org.springframework.stereotype.Service;
import ru.otus.homework01.bean.CustomMessageSourceEnvelop;
import ru.otus.homework01.bean.InOutEnvelope;
import ru.otus.homework01.dao.TestDao;
import ru.otus.homework01.domain.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReaderServiceImpl implements ReaderService {
    private final TestDao testDao;//источник данных
    private final TestResultService testResultService;//сервис для хранения результатов input'ов
    private final InOutEnvelope inOutEnvelope;
    private final CustomMessageSourceEnvelop customMessageSourceEnvelop;

    public ReaderServiceImpl(TestDao testDao,
                             TestResultService testResultService,
                             InOutEnvelope inOutEnvelope,
                             CustomMessageSourceEnvelop customMessageSourceEnvelop)
            throws IOException {
        this.testDao = testDao;
        this.testResultService = testResultService;
        this.inOutEnvelope = inOutEnvelope;
        this.customMessageSourceEnvelop = customMessageSourceEnvelop;
        startReadExam();
    }

    @Override
    public void showCustomRow(String value) {
        inOutEnvelope.getOut().println(value);
    }


    private void showQuestionRow(Question question) {
        StringBuilder sb = new StringBuilder();
        inOutEnvelope.getOut().println(question.getQuestionDescription());
        for (AnswerOption answerOptionObj : question.getListOfOptions()) {
            sb.append(answerOptionObj.getOrderNumber())
                    .append(")")
                    .append(answerOptionObj.getDescription())
                    .append('\n');
        }
        inOutEnvelope.getOut().println(sb.toString());
        inOutEnvelope.getOut().println(customMessageSourceEnvelop.getMessage("enter.answer"));
    }


    public String readRowStr() {
        return inOutEnvelope.getScanner().nextLine();
    }

    public int readRowInt() {
        return inOutEnvelope.getScanner().nextInt();
    }

    private void startReadExam() throws IOException {
        showCustomRow(customMessageSourceEnvelop.getMessage("enter.first-name"));
        checkStringInput();
        String firstName = readRowStr();
        showCustomRow(customMessageSourceEnvelop.getMessage("enter.last-name"));
        checkStringInput();
        String lastName = readRowStr();
        Student student = new Student(firstName, lastName);
        List<StudentAnswer> studentAnswers = new ArrayList<>();
        ExamTest examTest = testDao.getTest();
        for (Question question : examTest.getListOfQuestions()) {
            showQuestionRow(question);
            checkIntInput();
            int chosenOption = readRowInt();
            studentAnswers.add(new StudentAnswer(question, chosenOption));
        }
        inOutEnvelope.getScanner().close();
        TestResult result = testResultService.getEmptyTestResult();
        result.setStudent(student);
        result.setNumberOfQuestions(examTest.getListOfQuestions().size());
        result.setStudentAnswers(studentAnswers);
        result.setNumberOfCorrectAnswers(testResultService.getStudentCorrectAnswers(examTest, studentAnswers));
        testResultService.showResults(result);
    }

    private void checkStringInput() {
        String pattern = "[а-яА-Яa-zA-Z]+";
        while (!inOutEnvelope.getScanner().hasNext(pattern)) {
            inOutEnvelope.getOut().println(customMessageSourceEnvelop.getMessage(
                    "enter.string-value"));
            inOutEnvelope.getScanner().nextLine();
        }
    }

    private void checkIntInput() {
        while (!inOutEnvelope.getScanner().hasNextInt()) {
            inOutEnvelope.getOut().println(customMessageSourceEnvelop.getMessage(
                    "enter.int-value"));
            inOutEnvelope.getScanner().next();
        }
    }

}
