package ru.otus.books.model;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = Comment.COLLECTION_NAME)
@NoArgsConstructor
public class Comment {
    public static final String COLLECTION_NAME = "notes";

    @Id
    private String noteId;

    @Field(name = "comment")
    private String comment;

    @DBRef
    Book book;

    public Comment(String comment, Book book) {
        this.book = book;
        this.comment = comment;
    }
    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "noteId=" + noteId +
                ", comment='" + comment + '\'' +
                ", book=" + book +
                '}';
    }
}
