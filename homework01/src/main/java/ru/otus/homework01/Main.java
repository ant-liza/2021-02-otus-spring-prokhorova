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
        test.showQuestions();
    }

}
