package ru.otus.books.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.books.dao.AuthorDao;
import ru.otus.books.dao.BookDao;
import ru.otus.books.domain.Author;
import ru.otus.books.domain.Book;

import java.util.ArrayList;

@ShellComponent
public class BooksCommands {
    private final BookDao dao;
    private final AuthorDao authorDao;

    public BooksCommands(BookDao dao, AuthorDao authorDao) {
        this.dao = dao;
        this.authorDao = authorDao;
    }

    @ShellMethod(value = "getAllBooks", key = {"showAllBooks"})
    public void showAllBooks() {
        dao.getAllBooks().forEach(System.out::println);
    }

    @ShellMethod(value = "getCountBooks", key = {"countBooks"})
    public void countBooks() {
        System.out.println("Количество книг = " + dao.count());
    }

    @ShellMethod(key = "createBook", value = "create new book")
    public void createBook(@ShellOption("authorId") int authorId,
                           @ShellOption("catId") int bookCategoryId,
                           @ShellOption("name") String bookName) {
        Author existsAuthor = authorDao.getById(authorId);
        if (existsAuthor != null) {
            long newBookId = dao.insert(new Book(0, existsAuthor, new ArrayList<>(), bookName));
            System.out.println("Создана новая книга с id = " + newBookId);
        } else {
            System.out.println("Книга не добавлена: у книги должен быть автор");
        }
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
