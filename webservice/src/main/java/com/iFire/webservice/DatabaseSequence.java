package com.ifire.webservice;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "database_sequences")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseSequence {

    @Id
    private String id;

    private long seq;

    //getters and setters omitted
}