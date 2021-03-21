package ru.otus.homework01;

import org.springframework.context.annotation.*;

import java.io.IOException;
import java.util.Scanner;

@ComponentScan
@Configuration
@PropertySource("classpath:application.properties")
public class Main {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);
    }

}
