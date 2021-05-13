package ru.otus.books.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.books.exceptions.BookNotFoundException;
import ru.otus.books.models.Book;
import ru.otus.books.models.Note;
import ru.otus.books.repositories.jpa.BookRepository;
import ru.otus.books.repositories.jpa.NoteRepository;

import java.util.Optional;

@ShellComponent
public class NoteShellCommands {

    private final NoteRepository noteRepository;
    private final BookRepository bookRepository;


    public NoteShellCommands(NoteRepository noteRepository, BookRepository bookRepository) {
        this.noteRepository = noteRepository;
        this.bookRepository = bookRepository;
    }

    @ShellMethod(key = "showAllNotes", value = "desc")
    public void showAllNotes() {
        noteRepository.findAll().forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    @ShellMethod(key = "showAllNotesForBook", value = "desc")
    public void showAllNotesForBook(@ShellOption("id") long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            System.out.printf("Комментарии к книге %S:\n", book.get().getTitle());
            book.get().getNotes()
                    .forEach(n -> System.out.println(n.getComment()));
        }
    }

    @ShellMethod(key = "addNote", value = "desc")
    public void addNote(
            @ShellOption("comment") String comment,
            @ShellOption("book_id") long bookId) throws BookNotFoundException {
        Note note = new Note(0, comment);
        noteRepository.addNoteToBook(note, bookId);
    }

    @ShellMethod(key = "updateNoteComment", value = "desc")
    public void updateNoteComment(@ShellOption("id") long noteId,
                                  @ShellOption("comment") String comment) {
        Optional<Note> note = noteRepository.findById(noteId);
        note.ifPresent(n -> n.setComment(comment));
    }

    @ShellMethod(key = "deleteNote", value = "desc")
    public void deleteNote(@ShellOption("id") long noteId) {
        Optional<Note> note = noteRepository.findById(noteId);
        note.ifPresent(noteRepository::delete);
    }

}
