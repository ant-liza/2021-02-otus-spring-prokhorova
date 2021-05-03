package ru.otus.books.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.books.models.Author;
import ru.otus.books.repositories.jpa.custom.AuthorRepositoryCustom;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long>,
        AuthorRepositoryCustom {
    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);

    @Query("select a from Author a where a.firstName = :name")
    List<Author> findByFirstName(@Param("name") String firstname);

    List<Author> findByLastName(String lastname);

    List<Author> findByNickNameContaining(String nickname);

    List<Author> findByBooks_TitleContaining(String title);
}
