package ru.otus.homework01.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TestServiceImpl implements TestService {
    private final ReaderService readerService;

    public TestServiceImpl(ReaderService readerService) throws IOException{
        this.readerService = readerService;
    }

}
