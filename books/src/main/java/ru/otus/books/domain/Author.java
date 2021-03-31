package ru.otus.books.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Author {
    private final long authorId;
    private final String firstName;
    private final String lastName;
    private final String nickName;

}
