package ru.otus.homework01.domain;

import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.util.Scanner;

@Component
public class InOutEnvelope {
    private final Scanner scanner;
    private final PrintStream out;

    public InOutEnvelope() {
        this.scanner = new Scanner(System.in);
        this.out = System.out;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public PrintStream getOut() {
        return out;
    }
}
