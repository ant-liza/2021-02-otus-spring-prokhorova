package ru.otus.books.repositories.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.books.models.Book;
import ru.otus.books.repositories.jpa.BookRepositoryJpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryJpaImpl implements BookRepositoryJpa {
    @PersistenceContext
    EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public long count() {
        return em.createQuery("select count(*) from Book b", Long.class).getSingleResult();
    }

    @Transactional
    @Override
    public Book save(Book book) {
        if (book.getBookId() == 0) {
            em.persist(book);
            em.flush();
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Transactional
    @Override
    public void delete(Book book) {
        Query query = em.createQuery("delete from Book b where b.bookId =:id");
        query.setParameter("id", book.getBookId());
        query.executeUpdate();
        em.clear();
    }

    @Transactional
    @Override
    public void updateTitle(Book book, String title) {
        Query query = em.createQuery("update Book b set b.title=:title " +
                "where b.bookId = :id");
        query.setParameter("title", title);
        query.setParameter("id", book.getBookId());
        query.executeUpdate();
        em.refresh(book);
    }
}
