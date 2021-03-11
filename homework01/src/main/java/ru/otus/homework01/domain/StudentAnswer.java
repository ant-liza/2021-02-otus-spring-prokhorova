package ru.otus.homework01.domain;

public class StudentAnswer {
    Question question;
    int studentAnswer;

    public StudentAnswer(Question question, int studentAnswer) {
        this.question = question;
        this.studentAnswer = studentAnswer;
    }

    public Question getQuestion() {
        return question;
    }

    public int getStudentAnswer() {
        return studentAnswer;
    }
}
