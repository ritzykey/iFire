package com.ifire.webservice.bookstore;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private Long id;

    @Indexed(unique = true)
    private String title;

    private String author;

    private Integer pages;

    private Integer rating;
    
    private ArrayList<String> genres;
}
