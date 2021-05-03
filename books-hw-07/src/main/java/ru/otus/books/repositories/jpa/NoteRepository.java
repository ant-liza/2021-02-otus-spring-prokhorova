package ru.otus.books.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.books.models.Note;
import ru.otus.books.repositories.jpa.custom.NoteRepositoryCustom;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long>, NoteRepositoryCustom {

    @Query("select n from Book b join b.notes n where b.bookId=:id")
    List<Note> getAllNotesForBook(@Param("id") long bookId);

    List<Note> findByCommentContaining(String value);
}
