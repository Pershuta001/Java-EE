package com.example.lab7.controller;

import com.example.lab7.model.Book;
import io.restassured.RestAssured;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import java.util.Collection;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookRestControllerIntegrationTest {

    @LocalServerPort
    void savePort(final int port) {
        RestAssured.port = port;
    }
    @Test
    void shouldSendRequest() {
        given()
                    .body(BookRestController.class.getResourceAsStream("/request2.json"))
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                    .post("/add")
                .then()
                    .body("title", CoreMatchers.is("Dreamcatcher"))
                    .body("isbn", CoreMatchers.is("10236549"))
                    .body("authorName", CoreMatchers.is("Stephen King"));
    }

    @Test
    void shouldSendRequest_testResponseAsObject() {
        final Book responseBook = given()
                .body(BookRestController.class.getResourceAsStream("/request.json"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/add")
                .then()
                .extract()
                .as(Book.class);
        assertThat(responseBook).isEqualTo(Book.of("Dreamcatcher", "102365489","Stephen King"));
        assertThat(responseBook)
                .returns("Dreamcatcher", Book::getTitle)
                .returns("102365489", Book::getIsbn);
    }
    @Test
    void shouldSendRequest_returnCollectionOfBooksByParameter() {
        final Book responseBook = given()
                .body(BookRestController.class.getResourceAsStream("/request3.json"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/add")
                .then()
                .extract()
                .as(Book.class);
        final Collection<?> responseBooks = given()
                .queryParam("query", "10")
               .when()
                .get("/search")
                .then()
                .extract()
                .as(Collection.class);
        assertThat(responseBooks.contains(Book.of("Dreamcatcher", "10","Stephen King")));
    }
}