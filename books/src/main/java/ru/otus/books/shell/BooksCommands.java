package ru.otus.books.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.books.dao.BookDao;
import ru.otus.books.domain.Book;

@ShellComponent
public class BooksCommands {
    private final BookDao dao;

    public BooksCommands(BookDao dao) {
        this.dao = dao;
    }

    @ShellMethod(value = "getAllBooks", key = {"showAllBooks"})
    public void showAllBooks() {
        dao.getAll().forEach(System.out::println);
    }

    @ShellMethod(value = "getCountBooks", key = {"countBooks"})
    public void countBooks() {
        System.out.println("Количество книг = " + dao.count());
    }

    @ShellMethod(key = "createBook", value = "create new book")
    public void createBook(@ShellOption("authorId") int authorId,
                           @ShellOption("catId") int bookCategoryId,
                           @ShellOption("name") String bookName) {
        long newBookId = dao.insert(new Book(0, authorId, bookCategoryId, bookName));
        System.out.println("Создана новая книга с id = " + newBookId);
    }

    @ShellMethod(value = "deleteBook", key = {"deleteBook"})
    public void deleteBook(@ShellOption({"id"}) int bookId) {
        dao.deleteById(bookId);
        System.out.println("Удалена книга с ИД = " + bookId);
    }
    @ShellMethod(value = "get book", key = {"getBook"})
    public void getBook(@ShellOption("id") int bookId) {
        System.out.println("Книга с ИД = " + bookId + ": " + dao.getById(bookId));
    }
}
