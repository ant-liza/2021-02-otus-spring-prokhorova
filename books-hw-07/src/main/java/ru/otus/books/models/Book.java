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
@Table(name = "BOOKS")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long bookId;

    @ManyToMany(targetEntity = BookCategory.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "book_category_relation",
            joinColumns = @JoinColumn(name = "book_id",
                    foreignKey = @ForeignKey(name = "fk_bc_rel_book_id")),
            inverseJoinColumns = @JoinColumn(name = "book_category_id",
                    foreignKey = @ForeignKey(name = "fk_bc_rel_book_category_id")))
    private List<BookCategory> bookCategory;

    @OneToMany(targetEntity = Note.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "book_id")
    private List<Note> notes;

    @Column(name = "title", nullable = false)
    private String title;

}
