package ru.otus.books.repositories.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.books.models.Author;
import ru.otus.books.repositories.jpa.AuthorRepositoryJpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryJpaImpl implements AuthorRepositoryJpa {

    @PersistenceContext
    EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public long count() {
        return em.createQuery("select count(a) from Author a",
                Long.class).getSingleResult();
    }

    @Transactional
    @Override
    public Author save(Author author) {
        if (author.getAuthorId() == 0) {
            em.persist(author);
            em.flush();
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = em.createQuery("select a from Author a",Author.class);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public void delete(long id) {
        em.remove(em.find(Author.class, id));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateFirstName(Author author, String name) {
        Query query = em.createQuery("update Author a " +
                "set a.firstName = :name " +
                "where a.authorId = :id");
        query.setParameter("name", name);
        query.setParameter("id", author.getAuthorId());
        query.executeUpdate();
        save(author);
        em.refresh(author);// jpql запросы update/delete идут мимо кеша! поэтому refresh(),flush(),clear()!
    }
}
