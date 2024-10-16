package org.example.repository;


import org.example.entity.Book;

import java.time.LocalDate;
import java.util.List;

interface BookRepositoryCustom {
    List<Book> findBooksByCriteria(String authorName, String title, LocalDate fromDate, LocalDate toDate);
    List<Book> findAllBooks();
}

