package ru.otus.books.exceptions;

public class BookCategoryNotFoundException extends Exception{
    public BookCategoryNotFoundException(String message) {
        super(message);
    }
}
