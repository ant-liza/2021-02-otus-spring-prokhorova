package ru.otus.books.repository.custom;

import ru.otus.books.model.Comment;

public interface CommentRepositoryCustom {
    /**
     * Добавление комментария со сгенерированным ИД типа LONG
     * @param note - Объект комментарий
     */
    void insertCommentWithGeneratedId(Comment note);
}
