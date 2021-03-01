package ru.otus.homework01.domain;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

@DisplayName("Question Class")
public class QuestionTest {

    @DisplayName("Должен иметь заполненный список вариантов ответов")
    @Test
    public void shouldHaveNotEmptyAnswerOptions() {
        List<AnswerOption> answerOptions = new ArrayList<>();
        answerOptions.add(new AnswerOption(1, 1, "Черное"));
        Question question = new Question(1,
                "Какое из морей не омывает Турцию?", answerOptions, "Красное");
        Assertions.assertNotNull(answerOptions);
        Assertions.assertTrue(!answerOptions.isEmpty(), "Список вариантов ответов должен быть не пустым");
    }

    @DisplayName("Должен имень заполненный правильный ответ")
    @Test
    public void shouldHaveNotEmptyCorrectAnswer() {
        List<AnswerOption> answerOptions = new ArrayList<>();
        answerOptions.add(new AnswerOption(1, 1, "Черное"));
        Question question = new Question(1,
                "Какое из морей не омывает Турцию?", answerOptions, "ййй");
        Assertions.assertNotEquals("", question.getCorrectAnswer(),"Пустой правильный ответ");
    }

    @DisplayName("Должен иметь значение ID больше нуля")
    @Test
    public void shouldHaveQuestionIdNotNegative() {
        List<AnswerOption> answerOptions = new ArrayList<>();
        answerOptions.add(new AnswerOption(1, 1, "Черное"));
        Question question = new Question(-1,
                "Какое из морей не омывает Турцию?", answerOptions, "Q");
        Assertions.assertTrue(question.getQuestionId() > 0, "QuestionId должен быть больше 0");
    }

    @Test
    public void shouldHaveContainUniqueListOfAnswerOptions() {

    }

    @DisplayName("Должен содержать 4 варианта ответа")
    @Test
    public void shouldHaveContainOnlyFourAnswerOptions() {
        List<AnswerOption> answerOptions = new ArrayList<>();
        answerOptions.add(new AnswerOption(1, 1, "Черное"));
        answerOptions.add(new AnswerOption(1, 2, "Средиземное"));
        answerOptions.add(new AnswerOption(1, 3, "Красное"));
        answerOptions.add(new AnswerOption(1, 4, "Эгейское"));
        Question question = new Question(1,
                "Какое из морей не омывает Турцию?", answerOptions, "Красное");
        int expectedSize = 4;
        Assertions.assertEquals(expectedSize, question.getListOfOptions().size());
    }

    @DisplayName("Должен иметь правильный ответ из списка вариантов ответов")
    @Test
    public void shouldHaveContainCorrectAnswerFromAnswerOptions() {
        List<AnswerOption> answerOptions = new ArrayList<>();
        answerOptions.add(new AnswerOption(1, 1, "Черное"));
        answerOptions.add(new AnswerOption(1, 2, "Средиземное"));
        answerOptions.add(new AnswerOption(1, 3, "Красное"));
        answerOptions.add(new AnswerOption(1, 4, "Эгейское"));
        Question question = new Question(1,
                "Какое из морей не омывает Турцию?", answerOptions, "Красное");
        Assertions.assertTrue(answerOptionsContainsCorrectAnswer(answerOptions, question.getCorrectAnswer()));
    }

    private boolean answerOptionsContainsCorrectAnswer(List<AnswerOption> listOfAnswerOptions, String correctAnswer) {
        for (AnswerOption answOp : listOfAnswerOptions) {
            if (answOp.getDescription().equalsIgnoreCase(correctAnswer)) {
                return true;
            }
        }
        return false;
    }
}