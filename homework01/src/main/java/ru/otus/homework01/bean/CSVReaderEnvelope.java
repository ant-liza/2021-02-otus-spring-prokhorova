package ru.otus.homework01.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class CSVReaderEnvelope {
    private final BufferedReader reader;

    public CSVReaderEnvelope(@Value("${questions.file}")String sourceCSV) {
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
