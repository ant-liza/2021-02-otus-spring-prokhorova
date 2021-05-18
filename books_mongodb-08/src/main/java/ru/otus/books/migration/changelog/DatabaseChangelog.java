package ru.otus.books.migration.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.books.model.*;
import ru.otus.books.repository.*;

import java.util.Arrays;
import java.util.List;

/**
 * Класс для миграций данных
 */
@ChangeLog
public class DatabaseChangelog {

    private static final String AUTHOR = "EPROKHOROVA";
    private static final Long SEQUENCE_L = 0L;


    @ChangeSet(order = "001", id = "dropDb", author = AUTHOR, runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    /**
     * Сначала создаем таблицу с последовательностями, без нее невозможно будет
     * добавлять книги авторов и т.д., т.к. их ИД = Long
     */
    @ChangeSet(order = "002", id = "insertSequences", author = AUTHOR)
    public void insertSequences(SequenceRepository sequenceRepository) {
        Sequence authorSeq = new Sequence(Author.COLLECTION_NAME, SEQUENCE_L);
        Sequence bookSeq = new Sequence(Book.COLLECTION_NAME, SEQUENCE_L);
        Sequence bcSeq = new Sequence(BookCategory.COLLECTION_NAME, SEQUENCE_L);
        Sequence noteSeq = new Sequence(Comment.COLLECTION_NAME, SEQUENCE_L);
        List<Sequence> seqList = Arrays.asList(authorSeq, bookSeq, bcSeq, noteSeq);
        sequenceRepository.saveAll(seqList);
    }

    @ChangeSet(order = "003", id = "insertBookCategories", author = AUTHOR)
    public void insertBookCategories(BookCategoryRepository bookCategoryRepository) {
        BookCategory bcOne = new BookCategory("Детектив");
        BookCategory bcTwo = new BookCategory("Фантастика");
        BookCategory bcThree = new BookCategory("Роман");
        List<BookCategory> bcList = Arrays.asList(bcOne, bcTwo, bcThree);
        bcList.forEach(bookCategoryRepository::insertBookCategoryWithGeneratedId);
    }

    @ChangeSet(order = "004", id = "insertBooks", author = AUTHOR)
    public void insertBooks(BookRepository bookRepository, BookCategoryRepository bookCategoryRepository)  {
        final List<BookCategory> bcDetective = bookCategoryRepository.findByBookCategoryName("Детектив");
        final List<BookCategory> bcFiction = bookCategoryRepository.findByBookCategoryName("Фантастика");
        Book bcOne = new Book("Десять негритят", bcDetective);
        Book bcTwo = new Book("Вилла Белый Конь",bcDetective);
        Book bcThree = new Book("Стрелок",bcFiction);
        Book bcFour = new Book("Извлечение троих",bcFiction);
        Book bcFive = new Book("Мистер Мерседес",bcDetective);
        Book bcSix = new Book("Кто нашел, берет себе",bcDetective);
        Book bcSeven = new Book("Пост сдал",bcFiction);
        Book bcEight = new Book("Игра престолов",bcFiction);
        List<Book> booksList = Arrays.asList(bcOne, bcTwo, bcThree, bcFour, bcFive, bcSix, bcSeven, bcEight);
        booksList.forEach(bookRepository::insertBookWithGeneratedId);
    }

    @ChangeSet(order = "005", id = "insertAuthors", author = AUTHOR)
    public void insertAuthors(AuthorRepository authorRepository, BookRepository br) {
        Author authorOne = new Author("Agatha", "Christie", "");
        Author authorTwo = new Author("Stephen", "King", "");
        Author authorThree = new Author("George", "Martin,", "");
        List<Author> authorList = Arrays.asList(authorOne, authorTwo, authorThree);
        authorList.forEach(authorRepository::insertAuthorWithGeneratedId);
    }

    @ChangeSet(order = "006", id = "insertNotes", author = AUTHOR)
    public void insertNotes(CommentRepository noteRepository, BookRepository br) {
        Comment noteOne = new Comment("good",br.findByTitle("Стрелок").get());
        Comment noteTwo = new Comment("bad", br.findByTitle("Игра престолов").get());
        Comment noteThree = new Comment("medium",br.findByTitle("Пост сдал").get());
        List<Comment> notesList = Arrays.asList(noteOne, noteTwo, noteThree);
        notesList.forEach(noteRepository::insertCommentWithGeneratedId);
    }
}
