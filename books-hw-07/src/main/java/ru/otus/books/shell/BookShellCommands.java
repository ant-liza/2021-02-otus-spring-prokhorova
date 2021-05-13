package ru.otus.books.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.books.exceptions.BookCategoryNotFoundException;
import ru.otus.books.exceptions.BookNotFoundException;
import ru.otus.books.models.Book;
import ru.otus.books.models.Note;
import ru.otus.books.repositories.jpa.BookCategoryRepository;
import ru.otus.books.repositories.jpa.BookRepository;
import ru.otus.books.repositories.jpa.NoteRepository;

import java.util.List;
import java.util.Optional;

@ShellComponent
public class BookShellCommands {

    private final BookRepository bookRepository;
    private final NoteRepository noteRepository;


    public BookShellCommands(BookRepository bookRepository,
                             NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
        this.bookRepository = bookRepository;
    }

    @ShellMethod(key = "showAllBooks", value = "Вывести список всех книг")
    public void showAllBooks() {
        System.out.println("Список книг:");
        bookRepository.findAll().forEach(b -> System.out.printf("ИД = %d, %s\n", b.getBookId(),
                b.getTitle()));
    }

    @Transactional(readOnly = true)
    @ShellMethod(key = "showBookWithNotes", value = " book witn notes")
    public void showBookWithNotes(@ShellOption("title") String title) {
        Optional<Book> book = bookRepository.findByTitle(title);
        if (book.isPresent()) {
            List<Note> notes = book.get().getNotes();
            notes.forEach(n -> System.out.println(n.getComment()));
        } else {
            System.out.printf("Книга с наименованием %s не найдена\n", title);
        }
    }

    @ShellMethod(key = "addBook", value = "Adding new book")
    public void addBook(@ShellOption("book title") String title,
                        @ShellOption("category id") long bookCategoryId)
            throws BookCategoryNotFoundException {
        bookRepository.addBook(title, bookCategoryId);
    }

    @ShellMethod(key = "deleteBook", value = "delete")
    public void deleteBook(@ShellOption("id") long id) throws BookNotFoundException {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.deleteBookWithRelatedRecords(id);
            if (bookRepository.findById(book.get().getBookId()).isEmpty()) {
                System.out.printf("Книга с идентификатором %d удалена\n", id);
            }
        } else {
            throw new BookNotFoundException("Книга с идентификатором " + id + " не найдена");
        }
    }

}
