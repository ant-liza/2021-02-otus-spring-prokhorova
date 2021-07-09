package ru.otus.books;

public enum Pages {
    MAIN_PAGE("main.html", "main"),
    AUTHORS_PAGE("authors.html", "authors"),
    EDIT_AUTHOR_PAGE("author.html", "edit_author"),
    BOOKS_PAGE("books.html", "books");

    private final String templateName;
    private final String nameWithoutExtension;

    Pages(String templateName, String nameWithoutExtension) {
        this.templateName = templateName;
        this.nameWithoutExtension = nameWithoutExtension;
    }

    public String getTemplateName() {
        return templateName;
    }

    public String getNameWithoutExtension() {
        return nameWithoutExtension;
    }
}
