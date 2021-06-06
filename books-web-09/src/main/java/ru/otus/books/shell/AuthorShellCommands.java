package ru.otus.books.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.books.model.Author;
import ru.otus.books.model.Book;
import ru.otus.books.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@ShellComponent
public class AuthorShellCommands {
    private final AuthorRepository authorRepository;

    public AuthorShellCommands(AuthorRepository authorRepositoryJpa) {
        this.authorRepository = authorRepositoryJpa;
    }

    @ShellMethod(key = "showAllAuthors", value = "List of all authors")
    public void showAllAuthors() {
        System.out.println("Список авторов:");
        authorRepository.findAll().
                forEach(a -> System.out.printf("ИД = %d, %s %s\n", a.getAuthorId(), a.getFirstName(), a.getLastName()));
    }


    @ShellMethod(key = "showAuthorBooks", value = "Get author with its books by first and lastname")
    public void showAuthorBooks(@ShellOption("id") String id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isPresent()) {
            final Author author = authorOptional.get();
            List<Book> books = author.getBooks();
            if (books == null || books.isEmpty()) {
                System.out.println("У автора отсутствуют книги");
            } else {
                System.out.printf("Автор %s %s, список книг:\n", author.getFirstName(), author.getLastName());
                books.forEach(b -> System.out.println(b.getTitle()));
            }
        } else {
            System.out.printf("Автор c id %d  не найден \n", id);
        }
    }

    @ShellMethod(key = "addAuthor", value = "Adding new author")
    public void addAuthor(@ShellOption("firstName") String firstName,
                          @ShellOption("lastName") String lastName) {
        Author author = new Author(firstName, lastName, "");
        authorRepository.save(author);
        Optional<Author> existsAuthor = authorRepository.findById(author.getAuthorId());
        existsAuthor.ifPresent(value -> System.out.printf("Добавлен новый автор: имя - %s, фамилия - %s \n",
                value.getFirstName(), value.getLastName()));
    }

    @ShellMethod(key = "deleteAuthor", value = "delete")
    public void deleteAuthor(@ShellOption("id") String id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            authorRepository.delete(author.get());
            if (authorRepository.findById(author.get().getAuthorId()).isEmpty()) {
                System.out.printf("Автор с идентификатором %d удален\n", id);
            }
        } else {
            System.out.printf("Автор с идентификатором %d не найден", id);
        }
    }

   /* @ShellMethod(key = "addBookToAuthor", value = "add Book To Author")
    public void addBookToAuthor(@ShellOption("authorId") String authorId,
                                @ShellOption("bookId") String bookId) {
        authorRepository.addBookToAuthor(authorId, bookId);
    }*/


}
