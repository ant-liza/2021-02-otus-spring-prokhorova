package ru.otus.homework01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.homework01.domain.TestResult;
import ru.otus.homework01.service.TestResultService;
import ru.otus.homework01.service.TestService;

import java.io.IOException;

@ComponentScan
@Configuration
@PropertySource("classpath:application.properties")
public class Main {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);
        TestResultService testResultService = context.getBean(TestResultService.class);
        TestResult testResult = testResultService.getEmptyTestResult();// получили пустой объект для результатов тестирования
        TestService testService = context.getBean(TestService.class);
        testService.startTest(testResult);

        testResultService.showResults(testResult);
    }

}
