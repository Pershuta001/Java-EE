package com.example.lab2.model;

import lombok.*;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
public class Book {

    private String title;
    private String isbn;
    private String authorName;

}
