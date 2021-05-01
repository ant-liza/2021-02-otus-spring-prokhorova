package ru.otus.books.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.books.models.Book;
import ru.otus.books.models.BookCategory;
import ru.otus.books.models.Note;
import ru.otus.books.repositories.jpa.BookCategoryRepositoryJpa;
import ru.otus.books.repositories.jpa.BookRepositoryJpa;
import ru.otus.books.repositories.jpa.NoteRepositoryJpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ShellComponent
public class BookShellCommands {

    private final BookRepositoryJpa bookRepositoryJpa;
    private final BookCategoryRepositoryJpa bookCategoryRepositoryJpa;
    private final NoteRepositoryJpa noteRepositoryJpa;


    public BookShellCommands(BookRepositoryJpa bookRepositoryJpa,
                             NoteRepositoryJpa noteRepositoryJpa,
                             BookCategoryRepositoryJpa bookCategoryRepositoryJpa) {
        this.noteRepositoryJpa = noteRepositoryJpa;
        this.bookRepositoryJpa = bookRepositoryJpa;
        this.bookCategoryRepositoryJpa = bookCategoryRepositoryJpa;
    }

    @ShellMethod(key = "showAllBooks", value = "Вывести список всех книг")
    public void showAllBooks() {
        System.out.println("Список книг:");
        bookRepositoryJpa.findAll().forEach(b -> System.out.printf("ИД = %d, %s\n", b.getBookId(),
                b.getTitle()));
    }

    @ShellMethod(key = "showBookWithNotes", value = " book witn notes")
    public void showBookWithNotes(@ShellOption("title") String title) {
        Optional<Book> book = bookRepositoryJpa.findByTitle(title);
        if (book.isPresent()) {
            List<Note> notes = noteRepositoryJpa.getAllNotesForBook(book.get().getBookId());
            notes.forEach(n -> System.out.println(n.getComment()));
        } else {
            System.out.printf("Книга с наименованием %s не найдена\n", title);
        }
    }

    @ShellMethod(key = "addBook", value = "Adding new book")
    public void addBook(@ShellOption("book title") String title,
                        @ShellOption("category name") String bookCategoryName) {
        Optional<BookCategory> bc = bookCategoryRepositoryJpa.findByBookCategoryName(bookCategoryName);
        if (bc.isEmpty()) {
            System.out.printf("Жанр %s не найден\n", bookCategoryName);
        } else {
            List<BookCategory> bcList = new ArrayList<>();
            bcList.add(bc.get());
            Book book = new Book(0, bcList, null, title);
            bookRepositoryJpa.saveAndFlush(book);
        }
    }

    @ShellMethod(key = "deleteBook", value = "delete")
    public void deleteBook(@ShellOption("id") long id) {
        Optional<Book> book = bookRepositoryJpa.findById(id);
        if (book.isPresent()) {
            bookRepositoryJpa.delete(book.get());
            if (bookRepositoryJpa.findById(book.get().getBookId()).isEmpty()) {
                System.out.printf("Книга с идентификатором %d удалена\n", id);
            }
        } else {
            System.out.printf("Книга с идентификатором %d не найдена", id);
        }
    }

}
