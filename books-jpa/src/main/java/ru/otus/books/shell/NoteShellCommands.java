package ru.otus.books.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.books.models.Book;
import ru.otus.books.models.Note;
import ru.otus.books.repositories.jpa.BookRepositoryJpa;
import ru.otus.books.repositories.jpa.NoteRepositoryJpa;

import java.util.List;
import java.util.Optional;

@ShellComponent
public class NoteShellCommands {

    private final NoteRepositoryJpa noteRepositoryJpa;
    private final BookRepositoryJpa bookRepositoryJpa;


    public NoteShellCommands(NoteRepositoryJpa noteRepositoryJpa, BookRepositoryJpa bookRepositoryJpa) {
        this.noteRepositoryJpa = noteRepositoryJpa;
        this.bookRepositoryJpa = bookRepositoryJpa;
    }

    @ShellMethod(key = "showAllNotes", value = "desc")
    public void showAllNotes() {
        noteRepositoryJpa.findAll().forEach(System.out::println);
    }

    @ShellMethod(key = "showAllNotesForBook", value = "desc")
    public void showAllNotesForBook(@ShellOption("id") long bookId) {
        Optional<Book> book = bookRepositoryJpa.findById(bookId);
        if (book.isPresent()) {
            System.out.printf("Комментарии к книге %S:\n", book.get().getTitle());
            noteRepositoryJpa.findAllByBookId(bookId)
                    .forEach(n -> System.out.println(n.getComment()));
        }
    }

    @ShellMethod(key = "addNote", value = "desc")
    public void addNote(@ShellOption("comment") String comment, @ShellOption("book_id") long bookId) {
        Optional<Book> book = bookRepositoryJpa.findById(bookId);
        if (book.isPresent()) {
            Note note = new Note(0, comment);
            noteRepositoryJpa.save(note);
            List<Note> notes = book.get().getNotes();
            notes.add(note);
            book.get().setNotes(notes);
            bookRepositoryJpa.save(book.get());
        }
    }

    @ShellMethod(key = "updateNoteComment", value = "desc")
    public void updateNoteComment(@ShellOption("id") long noteId,
                                  @ShellOption("comment") String comment) {
        Optional<Note> note = noteRepositoryJpa.findById(noteId);
        note.ifPresent(n -> n.setComment(comment));
    }

    @ShellMethod(key = "deleteNote", value = "desc")
    public void deleteNote(@ShellOption("id") long noteId) {
        Optional<Note> note = noteRepositoryJpa.findById(noteId);
        note.ifPresent(noteRepositoryJpa::delete);
    }

}
