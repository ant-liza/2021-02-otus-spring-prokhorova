package ru.otus.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.books.domain.Note;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByCommentContaining(String value);
}
