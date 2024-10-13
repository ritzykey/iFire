package com.ifire.webservice.bookstore;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, Long> {
}