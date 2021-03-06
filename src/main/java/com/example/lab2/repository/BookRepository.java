package com.example.lab2.repository;

import com.example.lab2.model.Book;
import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookRepository {

    private static final Map<String, Book> database = new HashMap<>();//initDatabase();

    public void addNewBook(@NotNull final Book book){
        database.put(book.getIsbn(), book);
    }

    public Book getByIsbn(@NotNull final String isbn){
        return database.get(isbn);
    }

    public boolean containsIsbn(@NotNull final String isbn){
        if(database.containsKey(isbn))
            return true;
        return false;
    }
    public Collection<Book> getAllBooks(){
        return database.values();
    }

    public Collection<Book> getBooksByQuery(@NotNull final String query) {
        Collection<Book> values = database.values();
        Collection<Book> res = new ArrayList<>();
        for(Book book : values){
            if(book.getTitle().contains(query)){
                res.add(book);
                continue;
            }
            if(book.getIsbn().contains(query)){
                res.add(book);
                continue;
            }
            if(book.getAuthorName().contains(query)){
                res.add(book);
            }
        }
        return res;
    }

    private static Map<String, Book> initDatabase() {
        HashMap<String, Book> map = new HashMap<>();
        map.put("1",  Book.of("book1","1","author1"));
        map.put("2", Book.of("book2","2","author2"));
        map.put("3", Book.of("book3","3","author3"));
        return map;
    }
}
