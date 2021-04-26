package ru.otus.books.repositories.impl;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.books.models.Note;
import ru.otus.books.repositories.jpa.NoteRepositoryJpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class NoteRepositoryJpaImpl implements NoteRepositoryJpa {
    @PersistenceContext
    EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public long count() {
        return em.createQuery("select count(*) from Note n", Long.class).getSingleResult();
    }

    @Transactional
    @Override
    public Note save(Note note) {
        if (note.getNoteId() == 0) {
            em.persist(note);
            em.flush();
            return note;
        } else {
            return em.merge(note);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Note> findAll() {
        return em.createQuery("select n from Note n", Note.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Note> findById(long id) {
        return Optional.ofNullable(em.find(Note.class, id));
    }

    @Transactional
    @Override
    public void delete(Note note) {
        em.remove(note);
    }

    @Transactional
    @Override
    public void updateComment(Note note, String comment) {
        Query query = em.createQuery("update Note n set n.comment =:comment");
        query.setParameter("comment",comment);
        query.executeUpdate();
        em.clear();
    }
}
