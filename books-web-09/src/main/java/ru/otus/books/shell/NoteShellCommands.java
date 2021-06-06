package ru.otus.books.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.books.model.Comment;
import ru.otus.books.repository.BookRepository;
import ru.otus.books.repository.CommentRepository;

import java.util.Optional;

@ShellComponent
public class NoteShellCommands {

    private final CommentRepository noteRepository;
    private final BookRepository bookRepository;


    public NoteShellCommands(CommentRepository noteRepository, BookRepository bookRepository) {
        this.noteRepository = noteRepository;
        this.bookRepository = bookRepository;
    }

    @ShellMethod(key = "showAllNotes", value = "desc")
    public void showAllNotes() {
        noteRepository.findAll().forEach(System.out::println);
    }


    @ShellMethod(key = "showAllNotesForBook", value = "desc")
    public void showAllNotesForBook(@ShellOption("id") String bookId) {
        System.out.printf("Комментарии к книге %S:\n", bookRepository.findById(bookId).get().getTitle());
        noteRepository.findByBook(bookId)
                .forEach(n -> System.out.println(n.getComment()));

    }

    @ShellMethod(key = "addNote", value = "desc")
    public void addNote(
            @ShellOption("comment") String comment,
            @ShellOption("book_id") String bookId) {
        Comment note = new Comment(comment, bookRepository.findById(bookId).get());
        //noteRepository.insertCommentWithGeneratedId(note);
    }

    @ShellMethod(key = "updateNoteComment", value = "desc")
    public void updateNoteComment(@ShellOption("id") String noteId,
                                  @ShellOption("comment") String comment) {
        Optional<Comment> note = noteRepository.findById(noteId);
        note.ifPresent(n -> n.setComment(comment));
    }

    @ShellMethod(key = "deleteNote", value = "desc")
    public void deleteNote(@ShellOption("id") String noteId) {
        Optional<Comment> note = noteRepository.findById(noteId);
        note.ifPresent(noteRepository::delete);
    }

}
