package ru.otus.books.repositories.relations;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthorBookRelation {
    private final long authorId;
    private final long bookId;
}
