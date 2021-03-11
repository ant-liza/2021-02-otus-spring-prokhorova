package ru.otus.homework01.service;

import org.springframework.stereotype.Service;
import ru.otus.homework01.dao.TestDao;
import ru.otus.homework01.domain.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class TestServiceImpl implements TestService {
    private final TestDao dao;
    private final String pattern = "[а-яА-Яa-zA-Z]+";

    public TestServiceImpl(TestDao dao) {
        this.dao = dao;
    }

    @Override
    public ExamTest getTest() throws IOException {
        return dao.getTest();
    }

    public void startTest(TestResult testResult) throws IOException {
        ExamTest exam = getTest();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя");
        checkName(scanner);
        String firstName = scanner.nextLine();
        System.out.println("Введите фамилию");
        checkName(scanner);
        String lastName = scanner.nextLine();
        Student student = new Student(firstName, lastName);
        List<StudentAnswer> studentAnswers = new ArrayList<>();
        for (Question question : exam.getListOfQuestions()) {
            System.out.println(question.getQuestionDescription());
            StringBuilder sb = new StringBuilder();
            for (AnswerOption answerOptionObj : question.getListOfOptions()) {
                sb.append(answerOptionObj.getOrderNumber())
                        .append(")")
                        .append(answerOptionObj.getDescription())
                        .append('\n');
            }
            System.out.println(sb.toString());
            System.out.println("Введите ответ (1-4): ");
            checkIntInAnswers(scanner);
            int chosenOption = scanner.nextInt();
            while (chosenOption > 4 || chosenOption < 1) {
                System.out.println("Введите число от 1 до 4");
                chosenOption = scanner.nextInt();
            }
            studentAnswers.add(new StudentAnswer(question, chosenOption));
            System.out.println(question.getNumOfCorrectAnswer());
        }
        scanner.close();
        testResult.setStudent(student);
        testResult.setNumberOfQuestions(exam.getListOfQuestions().size());
        testResult.setStudentAnswers(studentAnswers);
        testResult.setNumberOfCorrectAnswers(getStudentCorrectAnswers(exam,studentAnswers));
    }



    private void checkName(Scanner scanner) {
        while (!scanner.hasNext(pattern)) {
            System.out.println("Фамилия должна содержать только буквы");
            scanner.nextLine();
        }
    }

    private void checkIntInAnswers(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Введите число от 1 до 4");
            scanner.next();
        }
    }

    private int getStudentCorrectAnswers(ExamTest exam, List<StudentAnswer> studentAnswers) {
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
