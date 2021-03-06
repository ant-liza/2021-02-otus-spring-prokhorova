package ru.otus.books.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUTHOR_ID")
    private long authorId;

    @Column(name = "FIRSTNAME", nullable = false)
    private String firstName;

    @Column(name = "LASTNAME", nullable = false)
    private String lastName;

    @Column(name = "NICKNAME")
    private String nickName;

    @OneToMany(targetEntity = Book.class, cascade = CascadeType.MERGE, fetch = FetchType.LAZY,
            orphanRemoval = true)//при удалении книги удаляем ее из БД, т.к. книга без автора не нужна
    @JoinColumn(name = "author_id")
    private List<Book> books;

    @Override
    public String toString() {
        return "\nAuthor{" +
                "\n id=" + authorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", \n books=" + books +
                '}';
    }
}
