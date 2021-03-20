package com.example.lab2.validation;

import com.example.lab2.exceptions.EmptyBookFieldException;
import com.example.lab2.exceptions.ExistingIsbnException;
import com.example.lab2.model.Book;
import com.example.lab2.repository.BookRepository;
import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Component;

@Component
public class BookValidator {

    private final BookRepository bookRepository;

    public BookValidator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void validateBook(@NotNull final Book book){
        validateIsbn(book.getIsbn());
        if(book.getTitle() == null || book.getTitle().equals("")){
            throw new EmptyBookFieldException("Value of title field can`t be empty");
        }
        if(book.getAuthorName() == null || book.getAuthorName().equals("")){
            throw new EmptyBookFieldException("Value of authorName field can`t be empty");
        }

    }
    private void validateIsbn(@NotNull final String isbn) throws ExistingIsbnException, EmptyBookFieldException {
        if(bookRepository.findById(isbn).isPresent()){
            throw new ExistingIsbnException("Such isbn already exists");
        }
        if(isbn.length() == 0)
            throw new EmptyBookFieldException("Value of Isbn field can`t be empty");
    }
}
