package ru.otus.books.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Author {
    private long authorId;
    private String firstName;
    private String lastName;
    private String nickName;
    private List<Book> books;

}
