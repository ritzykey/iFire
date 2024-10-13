package com.ifire.webservice.bookstore;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ifire.webservice.DatabaseSequence;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    private MongoOperations mongoOperations;

    @PostMapping("/addBook")
    public Book addBook(@RequestBody Book book) {
        Book bookTemp = new Book();
        bookTemp.setId(generateSequence(Book.SEQUENCE_NAME));
        bookTemp.setTitle(book.getTitle());
        bookTemp.setAuthor(book.getAuthor());
        bookTemp.setPages(book.getPages());
        bookTemp.setRating(book.getRating());
        bookTemp.setGenres(book.getGenres());
        bookRepository.save(bookTemp);
        return bookRepository.findById(bookTemp.getId()).orElse(null);
    }

    @GetMapping("/getBook/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @GetMapping("/getBooks")
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public long generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(Query.query(Criteria.where("_id").is(seqName)),
                new Update().inc("seq", 1), FindAndModifyOptions.options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

}
