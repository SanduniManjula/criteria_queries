package org.example.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.example.annotation.BookAnnotation;
import org.example.entity.Book;
import org.example.repository.BookRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Aspect
@Component
@Slf4j
public class SearchBookAspect {

    @Autowired
     public BookRepositoryImpl bookRepository;
/*
    @Before("@annotation(org.example.annotation.BookAnnotation) && args(title,..)")
    public void BookAnnotation(BookAnnotation bookAnnotation) {
        String title = bookAnnotation.title();

        if ("Book".equalsIgnoreCase(title)) {
            findBooksByCriteria(title);
        } else {
            throw new IllegalArgumentException("Invalid title: " + title);
        }
    }
    private void findBooksByCriteria(String authorName, String title, LocalDate fromDate, LocalDate toDate) {
        Book book = bookRepository.findBooksByCriteria(authorName, title, fromDate, toDate)
                .orElseThrow(() -> new RuntimeException("Book not found: " + title));
    }
 */

    @Before("@annotation(bookAnnotation) && args(title, authorName, fromDate, toDate, ..)")
    public void validateAndSearchBooks(JoinPoint joinPoint, BookAnnotation bookAnnotation, String title, String authorName, LocalDate fromDate, LocalDate toDate) {

        if (!"Book".equalsIgnoreCase(title)) {
            throw new IllegalArgumentException("Invalid title: " + title);
        }

        List<Book> books = bookRepository.findBooksByCriteria(authorName, title, fromDate, toDate);

        if (books.isEmpty()) {
            log.error("No books found with title: " + title);
            throw new RuntimeException("Book not found with title: " + title);
        }

        log.info("Found books: " + books);
    }

}
