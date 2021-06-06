package ru.otus.books.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.books.model.Book;
import ru.otus.books.model.Comment;
import ru.otus.books.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@ShellComponent
public class BookShellCommands {

    private final BookRepository bookRepository;


    public BookShellCommands(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @ShellMethod(key = "showAllBooks", value = "Вывести список всех книг")
    public void showAllBooks() {
        System.out.println("Список книг:");
        bookRepository.findAll().forEach(b -> System.out.printf("ИД = %d, %s\n", b.getBookId(),
                b.getTitle()));
    }

    /*@ShellMethod(key = "addBook", value = "Adding new book")
    public void addBook(@ShellOption("book title") String title) {
        Book book = new Book(title);
        bookRepository.insertBookWithGeneratedId(book);
    }*/

    @Transactional(readOnly = true)
    @ShellMethod(key = "showBookWithNotes", value = " book witn notes")
    public void showBookWithNotes(@ShellOption("title") String title) {
        Optional<Book> book = bookRepository.findByTitle(title);
        if (book.isPresent()) {
            List<Comment> notes = book.get().getNotes();
            if (notes != null) {
                notes.forEach(n -> System.out.println(n.getComment()));
            }
        } else {
            System.out.printf("Книга с наименованием %s не найдена\n", title);
        }
    }


    @ShellMethod(key = "deleteBook", value = "delete")
    public void deleteBook(@ShellOption("id") String id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            final Book book = bookOptional.get();
            bookRepository.delete(book);
            if (bookRepository.findById(id).isEmpty()) {
                System.out.printf("Книга с идентификатором %d удалена\n", id);
            }
        } else {
            System.out.println("Книга с идентификатором " + id + " не найдена");
        }
    }

}
