package ru.otus.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.books.domain.Author;
import ru.otus.books.repository.custom.jpa.AuthorRepositoryCustom;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long>, AuthorRepositoryCustom {

    @Query("select a from Author a where a.firstName = :name")
    List<Author> findByFirstName(@Param("name") String firstname);

    List<Author> findByLastName(String lastname);

    List<Author> findByNickNameContaining(String nickname);

    List<Author> findByBooks_TitleContaining(String title);
}
