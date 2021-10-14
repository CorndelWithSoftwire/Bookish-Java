package org.softwire.training.bookish.restService.controller;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.BookDict;
import org.softwire.training.bookish.models.database.Books;
import org.softwire.training.bookish.populateDB.PopulateDB;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BooksController {
    Jdbi jdbi = PopulateDB.createJdbiConnection();

    @GetMapping("books/{page}")
    List<BookDict> getAllBookNames(@PathVariable(value = "page", required = false) int page) {
        return new Books().getBooksList(this.jdbi, page);
    }

    @GetMapping("book/{id}")
    Book getBookById(@PathVariable(value = "id", required = true) int id) {
        return new Book().getBookById(this.jdbi, id);
    }


}
