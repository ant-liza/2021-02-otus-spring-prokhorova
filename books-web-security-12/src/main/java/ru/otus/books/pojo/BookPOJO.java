package ru.otus.books.pojo;

public class BookPOJO {
    private String bookId;
    private String title;
    private String authorId;
    private boolean isNewBook;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public boolean isNewBook() {
        return isNewBook;
    }

    public void setNewBook(boolean newBook) {
        isNewBook = newBook;
    }
}
