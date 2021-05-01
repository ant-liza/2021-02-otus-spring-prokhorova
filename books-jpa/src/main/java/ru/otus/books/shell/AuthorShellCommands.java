package ru.otus.books.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.books.models.Author;
import ru.otus.books.models.Book;
import ru.otus.books.repositories.jpa.AuthorRepositoryJpa;
import ru.otus.books.repositories.jpa.BookRepositoryJpa;

import java.util.List;
import java.util.Optional;

@ShellComponent
public class AuthorShellCommands {
    private final AuthorRepositoryJpa authorRepositoryJpa;
    private final BookRepositoryJpa bookRepositoryJpa;

    public AuthorShellCommands(AuthorRepositoryJpa authorRepositoryJpa,
                               BookRepositoryJpa bookRepositoryJpa) {
        this.authorRepositoryJpa = authorRepositoryJpa;
        this.bookRepositoryJpa = bookRepositoryJpa;

    }

    @ShellMethod(key = "showAllAuthors", value = "List of all authors")
    public void showAllAuthors() {
        System.out.println("Список авторов:");
        authorRepositoryJpa.findAll().
                forEach(a -> System.out.printf("ИД = %d, %s %s\n", a.getAuthorId(), a.getFirstName(), a.getLastName()));
    }

    @ShellMethod(key = "showAuthorBooks", value = "Get author with its books by first and lastname")
    public void showAuthorBooks(@ShellOption("firstName") String firstName,
                                @ShellOption("lastName") String lastName) {
        Optional<Author> author = authorRepositoryJpa.findByFirstNameAndLastName(firstName, lastName);
        if (author.isPresent()) {
            List<Book> books = bookRepositoryJpa.getAllBooksByAuthor(author.get().getAuthorId());
            if (books.isEmpty()) {
                System.out.println("У автора отсутствуют книги");
            } else {
                System.out.printf("Автор %s %s, список книг:\n", firstName, lastName);
                books.forEach(b -> System.out.println(b.getTitle()));
            }
        } else {
            System.out.printf("Автор: имя - %s, фамилия - %s  не найден \n", firstName, lastName);
        }
    }

    @ShellMethod(key = "addAuthor", value = "Adding new author")
    public void addAuthor(@ShellOption("firstName") String firstName,
                          @ShellOption("lastName") String lastName) {
        Author author = new Author(0, firstName, lastName, null, null);
        authorRepositoryJpa.saveAndFlush(author);
        Optional<Author> existsAuthor = authorRepositoryJpa.findById(author.getAuthorId());
        existsAuthor.ifPresent(value -> System.out.printf("Добавлен новый автор: имя - %s, фамилия - %s \n",
                value.getFirstName(), value.getLastName()));
    }

    @ShellMethod(key = "deleteAuthor", value = "delete")
    public void deleteAuthor(@ShellOption("id") long id) {
        Optional<Author> author = authorRepositoryJpa.findById(id);
        if (author.isPresent()) {
            authorRepositoryJpa.delete(author.get());
            if (authorRepositoryJpa.findById(author.get().getAuthorId()).isEmpty()) {
                System.out.printf("Автор с идентификатором %d удален\n", id);
            }
        } else {
            System.out.printf("Автор с идентификатором %d не найден", id);
        }
    }

    @ShellMethod(key = "updateAuthorFirstName", value = "update Author's First Name")
    public void updateAuthorFirstName(@ShellOption("id") long id,
                                      @ShellOption("firstName") String firstName) {
        Optional<Author> author = authorRepositoryJpa.findById(id);
        if (author.isPresent()) {
            author.get().setFirstName(firstName);
            authorRepositoryJpa.saveAndFlush(author.get());
        } else {
            System.out.printf("Автор с идентификатором %d не найден", id);
        }
    }

    @ShellMethod(key = "addBookToAuthor", value = "add Book To Author")
    public void addBookToAuthor(@ShellOption("firstName") String firstName,
                                @ShellOption("lastName") String lastName,
                                @ShellOption("title") String bookTitle) {
        Optional<Book> book = bookRepositoryJpa.findByTitle(bookTitle);
        Optional<Author> author = authorRepositoryJpa.findByFirstNameAndLastName(firstName, lastName);
        if (book.isPresent() && author.isPresent()) {
            author.get().setBooks(List.of(book.get()));
            authorRepositoryJpa.save(author.get());
        }
    }


}
