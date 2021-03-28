package ru.otus.homework01.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.homework01.service.StartExamService;

import java.io.IOException;

@ShellComponent
public class ShellExam {
    private final StartExamService startExamService;

    public ShellExam(StartExamService startExamService) {
        this.startExamService = startExamService;
    }


    @ShellMethod(value = "startExamFromShell", key = {"s","start"})
    public void start() throws IOException {
        startExamService.startExam();
    }
}
