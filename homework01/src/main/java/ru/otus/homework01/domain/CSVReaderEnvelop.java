package ru.otus.homework01.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class CSVReaderEnvelop {
    private final BufferedReader reader;

    public CSVReaderEnvelop(@Value("${questions.file}")String sourceCSV) {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(sourceCSV);
        if (inputStream == null) {
            throw new IllegalArgumentException("File not found! " + sourceCSV);
        } else {
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(streamReader);
        }
    }

    public BufferedReader getReader() {
        return reader;
    }

}
