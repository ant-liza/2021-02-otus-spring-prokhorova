package ru.otus.homework01.domain;

import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Objects;

public class Question {

    private int questionId;
    private String questionDescription;
    private List<AnswerOption> listOfOptions;
    private String correctAnswer;

    public Question(int questionId, String questionDescription,
                    @NonNull List<AnswerOption> listOfOptions,
                    String correctAnswer) {
        this.questionId = questionId;
        this.questionDescription = questionDescription;
        this.listOfOptions = listOfOptions;
        this.correctAnswer = correctAnswer;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public List<AnswerOption> getListOfOptions() {
        return listOfOptions;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public int getNumOfCorrectAnswer(){
        int correctNum = 0;
        for(AnswerOption answOpt : listOfOptions){
            if(answOpt.getDescription().equalsIgnoreCase(correctAnswer)){
                correctNum = answOpt.getOrderNumber();
            }
        }
        return correctNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return questionId == question.questionId ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, questionDescription, correctAnswer);
    }

    @Override
    public String toString() {
        return "\nQuestion[ " +
                " questionId: " + questionId +
                ", questionDescription: " + questionDescription +
                ", listOfOptions: " + listOfOptions +
                ", correctAnswer: " + correctAnswer+"]";
    }
}
