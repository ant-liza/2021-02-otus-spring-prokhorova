package ru.otus.books.migration.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.books.model.*;
import ru.otus.books.repository.*;

import java.util.Arrays;
import java.util.List;


@ChangeLog
public class DatabaseChangelog {

    private static final String AUTHOR = "EPROKHOROVA";

    @ChangeSet(order = "001", id = "dropDb", author = AUTHOR, runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }


    @ChangeSet(order = "003", id = "insertBookCategories", author = AUTHOR)
    public void insertBookCategories(BookCategoryRepository bookCategoryRepository) {
        BookCategory bcOne = new BookCategory("Детектив");
        BookCategory bcTwo = new BookCategory("Фантастика");
        BookCategory bcThree = new BookCategory("Роман");
        List<BookCategory> bcList = Arrays.asList(bcOne, bcTwo, bcThree);
        bcList.forEach(bookCategoryRepository::save);
    }

    @ChangeSet(order = "004", id = "insertAuthors", author = AUTHOR)
    public void insertAuthors(AuthorRepository authorRepository, BookRepository br) {
        Author authorOne = new Author("Agatha", "Christie", "");
        Author authorTwo = new Author("Stephen", "King", "");
        Author authorThree = new Author("George", "Martin", "");
        List<Author> authorList = Arrays.asList(authorOne, authorTwo, authorThree);
        authorList.forEach(authorRepository::save);
    }

    @ChangeSet(order = "005", id = "insertBooks", author = AUTHOR)
    public void insertBooks(BookRepository bookRepository, BookCategoryRepository bookCategoryRepository,
                            AuthorRepository authorRepository)  {
        final List<BookCategory> bcDetective = bookCategoryRepository.findByBookCategoryName("Детектив");
        final List<BookCategory> bcFiction = bookCategoryRepository.findByBookCategoryName("Фантастика");
        Author agatha = authorRepository.findByFirstName("Agatha").orElse(null);
        Author stephen = authorRepository.findByFirstName("Stephen").orElse(null);
        Author george = authorRepository.findByFirstName("George").orElse(null);
        Book bcOne = new Book("Десять негритят", bcDetective, agatha);
        Book bcTwo = new Book("Вилла Белый Конь",bcDetective,agatha);
        Book bcThree = new Book("Стрелок",bcFiction,stephen);
        Book bcFour = new Book("Извлечение троих",bcFiction,stephen);
        Book bcFive = new Book("Мистер Мерседес",bcDetective,stephen);
        Book bcSix = new Book("Кто нашел, берет себе",bcDetective,stephen);
        Book bcSeven = new Book("Пост сдал",bcFiction,stephen);
        Book bcEight = new Book("Игра престолов",bcFiction,george);
        List<Book> booksList = Arrays.asList(bcOne, bcTwo, bcThree, bcFour, bcFive, bcSix, bcSeven, bcEight);
        booksList.forEach(bookRepository::save);
    }

    @ChangeSet(order = "006", id = "insertNotes", author = AUTHOR)
    public void insertNotes(CommentRepository noteRepository, BookRepository br) {
        Comment noteOne = new Comment("good",br.findByTitle("Стрелок").get());
        Comment noteTwo = new Comment("bad", br.findByTitle("Игра престолов").get());
        Comment noteThree = new Comment("medium",br.findByTitle("Пост сдал").get());
        List<Comment> notesList = Arrays.asList(noteOne, noteTwo, noteThree);
        notesList.forEach(noteRepository::save);
    }

    @ChangeSet(order = "007", id = "insertUsers", author = AUTHOR)
    public void insertUsers(UserRepository userRepository) {
        UserMongo one = new UserMongo("ivanov", "$2y$10$6BQuAtBv6k2xQZXeu1Vp2.JO830/pG4SMUsD5Pe6Y6WBuPwb2J.NC");
        UserMongo two = new UserMongo("test", "$2y$10$w5e3WDexlsWg2ypu93Wsqe06gVlTTPZqiPmTrvWJL36kB9qAh2Tqa");
        UserMongo three = new UserMongo("admin", "$2y$10$BJIZye4dEq0CMOM9urF8sOAb38zqxmDjI4G59ggDzFBQ0PbE5zAOi");
        userRepository.save(one);
        userRepository.save(two);
        userRepository.save(three);
    }

}
