package ru.otus.homework01.domain;

import java.util.List;

/**
 * Класс для хранения результатов тестирования:
 * студент, ответы студента на вопросы, кол-во вопросов теста,
 * кол-во правильных ответов студента
 */
public class TestResult {
    private Student student;
    private int numberOfCorrectAnswers;
    private List<StudentAnswer> studentAnswers;
    private int numberOfQuestions;

    public TestResult(Student student, List<StudentAnswer> studentAnswers) {
        this.student = student;
        this.studentAnswers = studentAnswers;
    }

    public TestResult() {
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public List<StudentAnswer> getStudentAnswers() {
        return studentAnswers;
    }

    public Student getStudent() {
        return student;
    }

    public void setNumberOfCorrectAnswers(int numberOfCorrectAnswers) {
        this.numberOfCorrectAnswers = numberOfCorrectAnswers;
    }

    public int getNumberOfCorrectAnswers() {
        return numberOfCorrectAnswers;
    }


    public void setStudent(Student student) {
        this.student = student;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setStudentAnswers(List<StudentAnswer> studentAnswers) {
        this.studentAnswers = studentAnswers;
    }


    public String getStudentInfo() {
        return getStudent().getFirstName() + " " + getStudent().getLastName();
    }
}
