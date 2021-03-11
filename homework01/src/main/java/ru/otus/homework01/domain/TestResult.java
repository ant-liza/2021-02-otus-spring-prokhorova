package ru.otus.homework01.domain;

import java.util.List;

public class TestResult {
    Student student;
    int numberOfCorrectAnswers;
    List<StudentAnswer> studentAnswers;
    int numberOfQuestions;

    public TestResult(Student student,  List<StudentAnswer> studentAnswers) {
        this.student = student;
        this.studentAnswers = studentAnswers;
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
