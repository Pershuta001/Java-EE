package com.example.lab2.controller;

import com.example.lab2.model.Book;
import com.example.lab2.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "home-page";
    }

}
