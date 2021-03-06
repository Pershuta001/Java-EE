package com.example.lab2.service;

import com.example.lab2.model.Book;
import com.example.lab2.repository.BookRepository;
import com.example.lab2.validation.BookValidator;
import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class BookService {

    private BookRepository bookRepository;
    private BookValidator bookValidator;

    public Book addNewBook(@NotNull final Book book){
        bookValidator.validateBook(book);
        bookRepository.addNewBook(book);
        return book;
    }

    public Book getBookByIsbn(@NotNull final String isbn){
       return bookRepository.getByIsbn(isbn);
    }

    public Collection<Book> getAllBooks(){
        return bookRepository.getAllBooks();
    }

    public Collection<Book> getBooksByQuery(@NotNull final String query) {
        return bookRepository.getBooksByQuery(query);
    }
}
