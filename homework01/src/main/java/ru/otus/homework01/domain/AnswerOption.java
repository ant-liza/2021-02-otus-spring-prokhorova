package ru.otus.homework01.domain;
/**
 * Класс для вариантов ответов
 */
public class AnswerOption {
    private int questionID;
    private int orderNumber;
    private String description;

    public AnswerOption(int questionNumber, int orderNumber, String description) {
        this.questionID = questionNumber;
        this.orderNumber = orderNumber;
        this.description = description;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getQuestionID() {
        return questionID;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "\nAnswerOption[ "+ "questionID = " + questionID + ", orderNumber = " + orderNumber
                + ", description = " + description +"]";
    }
}
