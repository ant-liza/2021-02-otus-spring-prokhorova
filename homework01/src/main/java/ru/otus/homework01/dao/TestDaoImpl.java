package ru.otus.homework01.dao;

import org.springframework.stereotype.Component;
import ru.otus.homework01.domain.AnswerOption;
import ru.otus.homework01.bean.CSVReaderEnvelope;
import ru.otus.homework01.domain.ExamTest;
import ru.otus.homework01.domain.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TestDaoImpl implements TestDao {
    private final CSVReaderEnvelope csvReaderEnvelope;

    public TestDaoImpl(CSVReaderEnvelope csvReaderEnvelop) {
        this.csvReaderEnvelope = csvReaderEnvelop;
    }

    @Override
    public ExamTest getTest() throws IOException {
        ExamTest examTest = new ExamTest();
        List<Question> questions = new ArrayList<>();
        String line = csvReaderEnvelope.getReader().readLine();//пропускаем 1-ю строку с наименованием столбцов
        while ((line = csvReaderEnvelope.getReader().readLine()) != null && !line.isEmpty()) {
            String[] fields = line.split(";");
            String questionId = fields[0];
            String questionDescription = fields[1];
            String optionOne = fields[2];
            String optionTwo = fields[3];
            String optionThree = fields[4];
            String optionFour = fields[5];
            String correctAnswer = fields[6];

            List<AnswerOption> ansOpList = new ArrayList<>();
            AnswerOption ansOpObjOne = new AnswerOption(Integer.parseInt(questionId), 1, optionOne);
            AnswerOption ansOpObjTwo = new AnswerOption(Integer.parseInt(questionId), 2, optionTwo);
            AnswerOption ansOpObjThree = new AnswerOption(Integer.parseInt(questionId), 3, optionThree);
            AnswerOption ansOpObjFour = new AnswerOption(Integer.parseInt(questionId), 4, optionFour);

            ansOpList.add(ansOpObjOne);
            ansOpList.add(ansOpObjTwo);
            ansOpList.add(ansOpObjThree);
            ansOpList.add(ansOpObjFour);

            Question question = new Question(Integer.parseInt(questionId), questionDescription, ansOpList, correctAnswer);
            questions.add(question);
        }
        csvReaderEnvelope.getReader().close();
        examTest.setListOfQuestions(questions);
        examTest.setName("Test # 1");
        return examTest;
    }

}


