package ru.otus.books.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.books.model.Comment;
import ru.otus.books.repository.CommentRepository;
import ru.otus.books.repository.SequenceRepository;
import ru.otus.books.repository.custom.CommentRepositoryCustom;

public class CommentRepositoryImpl implements CommentRepositoryCustom {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    SequenceRepository sequenceRepository;
    @Override
    public void insertCommentWithGeneratedId(Comment note) {
        note.setNoteId(sequenceRepository.getNextSequenceId(Comment.COLLECTION_NAME));
        commentRepository.save(note);
    }
}
