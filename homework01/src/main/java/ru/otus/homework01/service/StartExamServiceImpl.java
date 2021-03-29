package ru.otus.homework01.service;

import org.springframework.stereotype.Service;
import ru.otus.homework01.bean.CustomMessageSourceEnvelop;
import ru.otus.homework01.dao.TestDao;
import ru.otus.homework01.domain.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StartExamServiceImpl implements StartExamService{
    private final ReaderService readerService;
    private final TestDao testDao;//источник данных
    private final CustomMessageSourceEnvelop customMessageSourceEnvelop;
    private final TestResultService testResultService;//сервис для хранения результатов input'ов

    public StartExamServiceImpl(ReaderService readerService,
                                CustomMessageSourceEnvelop customMessageSourceEnvelop,
                                TestResultService testResultService,
                                TestDao testDao){
        this.readerService = readerService;
        this.customMessageSourceEnvelop = customMessageSourceEnvelop;
        this.testResultService = testResultService;
        this.testDao = testDao;
    }
    public void startExam() throws IOException {
        Student student = new Student();
        introduceStudent(student);
        List<StudentAnswer> studentAnswers = new ArrayList<>();
        ExamTest examTest = testDao.getTest();
        printQuestionsAndFillStudentAnswers(studentAnswers, examTest.getListOfQuestions());
        showResults(student,examTest.getListOfQuestions().size(),studentAnswers,examTest);
    }

    private void introduceStudent(Student student){
        readerService.showCustomRow(customMessageSourceEnvelop.getMessage("enter.first-name"));
        String firstName = readerService.readRowStr();
        readerService.showCustomRow(customMessageSourceEnvelop.getMessage("enter.last-name"));
        String lastName = readerService.readRowStr();
        student.setFirstName(firstName);
        student.setLastName(lastName);
    }
    private void printQuestionsAndFillStudentAnswers(List<StudentAnswer> studentAnswers,
                                List<Question> listOfExamQuestions){
        for (Question question : listOfExamQuestions) {
            readerService.showQuestionRow(question);
            int chosenOption = readerService.readRowInt();
            studentAnswers.add(new StudentAnswer(question, chosenOption));
        }
    }

    private void showResults(Student student,
                             int numberOfQuestions,
                             List<StudentAnswer> studentAnswers,
                             ExamTest examTest){
        TestResult result = testResultService.getEmptyTestResult();
        result.setStudent(student);
        result.setNumberOfQuestions(numberOfQuestions);
        result.setStudentAnswers(studentAnswers);
        result.setNumberOfCorrectAnswers(testResultService.getStudentCorrectAnswers(examTest,
                studentAnswers));
        testResultService.showResults(result);
    }
}
