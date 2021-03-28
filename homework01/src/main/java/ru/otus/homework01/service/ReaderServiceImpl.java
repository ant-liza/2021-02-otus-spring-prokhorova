package ru.otus.homework01.service;

import org.springframework.stereotype.Service;
import ru.otus.homework01.bean.CustomMessageSourceEnvelop;
import ru.otus.homework01.bean.InOutEnvelope;
import ru.otus.homework01.domain.AnswerOption;
import ru.otus.homework01.domain.Question;

@Service
public class ReaderServiceImpl implements ReaderService {
    private final InOutEnvelope inOutEnvelope;
    private final CustomMessageSourceEnvelop customMessageSourceEnvelop;

    public ReaderServiceImpl(InOutEnvelope inOutEnvelope,
                             CustomMessageSourceEnvelop customMessageSourceEnvelop) {
        this.inOutEnvelope = inOutEnvelope;
        this.customMessageSourceEnvelop = customMessageSourceEnvelop;
    }

    @Override
    public void showCustomRow(String value) {
        inOutEnvelope.getOut().println(value);
    }


    public void showQuestionRow(Question question) {
        StringBuilder sb = new StringBuilder();
        inOutEnvelope.getOut().println(question.getQuestionDescription());
        for (AnswerOption answerOptionObj : question.getListOfOptions()) {
            sb.append(answerOptionObj.getOrderNumber())
                    .append(")")
                    .append(answerOptionObj.getDescription())
                    .append('\n');
        }
        inOutEnvelope.getOut().println(sb.toString());
        inOutEnvelope.getOut().println(customMessageSourceEnvelop.getMessage("enter.answer"));
    }


    public String readRowStr() {
        checkStringInput();
        return inOutEnvelope.getScanner().nextLine();
    }

    public int readRowInt() {
        checkIntInput();
        return inOutEnvelope.getScanner().nextInt();
    }


    private void checkStringInput() {
        String pattern = "[а-яА-Яa-zA-Z]+";
        while (!inOutEnvelope.getScanner().hasNext(pattern)) {
            inOutEnvelope.getOut().println(customMessageSourceEnvelop.getMessage(
                    "enter.string-value"));
            inOutEnvelope.getScanner().nextLine();
        }
    }

    private void checkIntInput() {
        while (!inOutEnvelope.getScanner().hasNextInt()) {
            inOutEnvelope.getOut().println(customMessageSourceEnvelop.getMessage(
                    "enter.int-value"));
            inOutEnvelope.getScanner().next();
        }
    }

}
