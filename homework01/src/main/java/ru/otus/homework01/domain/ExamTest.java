package ru.otus.homework01.domain;

import java.util.Date;
import java.util.List;

public class ExamTest {
    private String name;
    private List<Question> listOfQuestions;
    private int numberOfQuestions;
    private Date startDate;
    private Date endDate;
    private int numberOfCorrectAnswers;
    private double percentOfDone;
    private int mark;

    private String source;


    public void setName(String name) {
        this.name = name;
    }

    public void setListOfQuestions(List<Question> listOfQuestions) {
        this.listOfQuestions = listOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setNumberOfCorrectAnswers(int numberOfCorrectAnswers) {
        this.numberOfCorrectAnswers = numberOfCorrectAnswers;
    }

    public void setPercentOfDone(double percentOfDone) {
        this.percentOfDone = percentOfDone;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public List<Question> getListOfQuestions() {
        return listOfQuestions;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getNumberOfCorrectAnswers() {
        return numberOfCorrectAnswers;
    }

    public double getPercentOfDone() {
        return percentOfDone;
    }

    public int getMark() {
        return mark;
    }

    public String getSource() {
        return source;
    }

}
