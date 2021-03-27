package com.example.lab7.controller;

import com.example.lab7.model.Book;
import com.example.lab7.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;

    @ResponseBody
    @PostMapping(value = "/add")
    public ResponseEntity<Book> addNewBook(
            @RequestBody final Book book
    ) {
        bookService.addNewBook(book);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.getBookByIsbn(book.getIsbn()));
    }

    @ResponseBody
    @GetMapping(value = "/book-list")
    public Collection<Book> getBookList(
    ){
        return bookService.getAllBooks();
    }

    @ResponseBody
    @GetMapping(value = "/find-book")
    public Collection<Book> getBookByQuery(
            @RequestParam final String title
    ){
        return bookService.getBooksByTitle(title);
    }

}
