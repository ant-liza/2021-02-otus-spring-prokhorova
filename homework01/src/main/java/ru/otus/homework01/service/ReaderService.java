package ru.otus.homework01.service;

import ru.otus.homework01.domain.Question;

import java.util.Scanner;

public interface ReaderService {
    void showCustomRow(String value);
    String readRowStr();
    int readRowInt();
}
