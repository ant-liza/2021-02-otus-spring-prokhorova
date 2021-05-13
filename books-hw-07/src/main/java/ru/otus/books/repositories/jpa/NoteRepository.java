package ru.otus.books.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.books.models.Note;
import ru.otus.books.repositories.jpa.custom.NoteRepositoryCustom;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long>, NoteRepositoryCustom {

    List<Note> findByCommentContaining(String value);
}
