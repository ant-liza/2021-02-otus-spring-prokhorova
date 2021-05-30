package ru.otus.books.repository.custom;

import ru.otus.books.model.BookCategory;

public interface BookCategoryRepositoryCustom {
    /**
     * Добавление жанра со сгенерированным ИД типа LONG
     * @param bc - Объект жанр
     */
    void insertBookCategoryWithGeneratedId(BookCategory bc);


}
