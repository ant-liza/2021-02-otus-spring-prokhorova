package ru.otus.books.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "BOOK_CATEGORIES")
public class BookCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_CATEGORY_ID")
    private long bookCategoryId;

    @Column(name = "BOOK_CATEGORY_NAME", nullable = false, unique = true)
    private String bookCategoryName;
}
