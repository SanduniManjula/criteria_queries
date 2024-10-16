package org.example.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String author;
    double price;
    int page;
    LocalDate manufacturedDate;


    public Book(String title, String author,double price, int page,LocalDate manufacturedDate) {
        this.title = title;
        this.author = author;
        this.price=price;
        this.page=page;
        this.manufacturedDate=manufacturedDate;
    }

    static Specification<Book> hasAuthor(String author) {
        return (book, cq, cb) -> cb.equal(book.get("author"), author);
    }

    static Specification<Book> titleContains(String title) {
        return (book, cq, cb) -> cb.like(book.get("title"), "%" + title + "%");
    }
}
