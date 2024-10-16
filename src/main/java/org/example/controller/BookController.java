package org.example.controller;
import org.example.entity.Book;
import org.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books/search")
    public List<Book> searchBooks(
            @RequestParam(required = false) String authorName,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate) {


        if (authorName != null) {
            authorName = authorName.trim();
        }
        if (title != null) {
            title = title.trim();
        }


        LocalDate fromLocalDate = null;
        LocalDate toLocalDate = null;

        try {

            if (fromDate != null && !fromDate.trim().isEmpty()) {
                fromLocalDate = LocalDate.parse(fromDate.trim());
            }

            if (toDate != null && !toDate.trim().isEmpty()) {
                toLocalDate = LocalDate.parse(toDate.trim());
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format");
        }

        return bookService.searchBooks(authorName, title, fromLocalDate, toLocalDate);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleTypeMismatch(MethodArgumentTypeMismatchException ex){
        return "Invalid value provided: " +ex.getValue() + ". Error: " +ex.getCause().getMessage();
    }


    @GetMapping("/books/findall")
    public List<Book> searchAll(){
       return bookService.test();
      // return null;
    }


}
