package ru.otus.books.repositories.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.books.models.BookCategory;
import ru.otus.books.repositories.jpa.BookCategoryRepositoryJpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class BookCategoryRepositoryJpaImpl implements BookCategoryRepositoryJpa {
    @PersistenceContext
    EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public long count() {
        return em.createQuery("select count(*) from BookCategory bc", Long.class).getSingleResult();
    }

    @Transactional
    @Override
    public BookCategory save(BookCategory bc) {
        if (bc.getBookCategoryId() == 0) {
            em.persist(bc);
            em.flush();
            return bc;
        } else {
            return em.merge(bc);//меняет состояние на persistent!
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookCategory> findAll() {
        TypedQuery<BookCategory> query = em.createQuery("select bc from BookCategory bc",
                BookCategory.class);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<BookCategory> findById(long id) {
        return Optional.ofNullable(em.find(BookCategory.class, id));
    }

    @Override
    public void delete(BookCategory bc) {
        em.remove(bc);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateTitle(BookCategory bc, String title) {
        Query query = em.createQuery("update BookCategory bc " +
                "set bc.bookCategoryName=:name where bc.bookCategoryId =:id");
        query.setParameter("id", bc.getBookCategoryId());
        query.setParameter("name", title);
        query.executeUpdate();
        em.refresh(bc);
    }

}
