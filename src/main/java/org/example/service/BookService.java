package org.example.service;

import org.example.entity.Book;
import org.example.repository.BookRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepositoryImpl bookRepositoryCustom;

    public List<Book> searchBooks(String authorName, String title, LocalDate fromDate, LocalDate toDate) {
        return bookRepositoryCustom.findBooksByCriteria(authorName, title, fromDate, toDate);
    }
/*
    public void test() {
        List<Book> allBooks = bookRepositoryCustom.findAllBooks();
        System.out.println(allBooks);
    }
 */

    public List<Book> test(){
        System.out.println("All Books with Details");
        return bookRepositoryCustom.findAllBooks();
    }


}
