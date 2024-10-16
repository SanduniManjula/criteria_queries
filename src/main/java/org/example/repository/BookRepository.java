package org.example.repository;

import org.example.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {
}
