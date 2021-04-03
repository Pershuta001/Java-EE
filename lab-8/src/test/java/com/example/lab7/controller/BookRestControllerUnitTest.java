package com.example.lab7.controller;

import com.example.lab8.controller.BookRestController;
import com.example.lab8.model.Book;
import com.example.lab8.service.BookService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.InputStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookRestController.class)
class BookRestControllerUnitTest {

    @MockBean
    private BookService bookService;
    @Autowired
    private MockMvc mockMvc;


    @Test
    @SneakyThrows
    void shouldHandleLoginRequest() {
        when(bookService.addNewBook(any())).thenReturn(Book.of("Dreamcatcher", "102365489", "Stephen King"));
        InputStream resourceAsStream = BookRestControllerUnitTest.class.getResourceAsStream("/request.json");
        final byte[] bytes = new byte[resourceAsStream.available()];
        resourceAsStream.read(bytes);

        mockMvc.perform(
                post("/add")
                        .content(bytes)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
        verify(bookService).addNewBook(Book.of("Dreamcatcher", "102365489", "Stephen King"));
    }
}