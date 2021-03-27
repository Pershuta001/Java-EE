package com.example.lab7.controller;

import com.example.lab7.model.Book;
import com.example.lab7.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/")
    public String home(
            Model model) {
        return homePaged(1, model);
    }
    @GetMapping(value = "/{pageNo}")
    public String homePaged(
            @PathVariable final Integer pageNo,
            Model model) {
        Page<Book> page =  bookService.findPage(pageNo, 10);
        model.addAttribute("books",page.getContent());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("currentPage", pageNo);
        return "home-page";
    }

    @GetMapping(value = "/book/{isbn}")
    public String bookPage(
            @PathVariable final String isbn,
            Model model
    ){
        model.addAttribute("book",bookService.getBookByIsbn(isbn));
        return "book-page";
    }

}
