package com.ifire.webservice.photo;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection  = "photo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Photo {

    @Id
    private String id;

    private String title;

    private Binary image;
}
