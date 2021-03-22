package ru.otus.homework01.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

@DisplayName("Класс для сервиса по результатам тестировани")
public class TestResultServiceTest {
    @DisplayName("должен корректно рассчитывать оценку исходя из количества правильных ответов")
    @Test
    public void shouldCorrectCalculateMark() {
        HashMap<Integer, Integer[]> marksAndAmountOfCorrectQuestions = new HashMap<>();
        marksAndAmountOfCorrectQuestions.put(5, new Integer[5]);
        marksAndAmountOfCorrectQuestions.put(4, new Integer[4]);
        marksAndAmountOfCorrectQuestions.put(3, new Integer[3]);
        marksAndAmountOfCorrectQuestions.put(2, new Integer[]{0, 1, 2});
        int mark = getMarkByCorrectAnswers(marksAndAmountOfCorrectQuestions, 0);
        Assertions.assertEquals(2, mark);
    }

    private Integer getMarkByCorrectAnswers(HashMap<Integer, Integer[]>
                                                    marksAndAmountOfCorrectQuestions,
                                            int correctAnswers) {
        int mark = 2;
        for (Map.Entry<Integer, Integer[]> entry : marksAndAmountOfCorrectQuestions.entrySet()) {
            mark = entry.getKey();
            Integer[] correctAnswersForMark = entry.getValue();
            for (int i = 0; i < correctAnswersForMark.length; i++) {
                if (correctAnswers == correctAnswersForMark[i]) {
                    return mark;
                }
            }
        }
        return mark;
    }
}
