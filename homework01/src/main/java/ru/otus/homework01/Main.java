package ru.otus.homework01;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework01.domain.AnswerOption;
import ru.otus.homework01.domain.ExamTest;
import ru.otus.homework01.domain.Question;
import ru.otus.homework01.service.TestService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        TestService service = context.getBean(TestService.class);
        ExamTest test = service.loadTest();
        printTestQuestions(test);
    }

    private static void printTestQuestions(ExamTest examTest) {
        for (Question question : examTest.getListOfQuestions()) {
            System.out.println(question.getQuestionDescription());
            StringBuilder sb = new StringBuilder();
            for (AnswerOption answerOptionObj : question.getListOfOptions()) {
                sb.append(answerOptionObj.getOrderNumber())
                        .append(")")
                        .append(answerOptionObj.getDescription())
                        .append('\n');

            }
            System.out.println(sb.toString());
        }
    }

}
