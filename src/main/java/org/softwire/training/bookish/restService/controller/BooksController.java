package org.softwire.training.bookish.restService.controller;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.Books;
import org.softwire.training.bookish.populateDB.PopulateDB;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BooksController {

    @GetMapping("books")
    List<String> getAllBookNames() {
        Jdbi jdbi = PopulateDB.createJdbiConnection();
        List<Book> allBooks = new Books().getBooksList(jdbi);
        return allBooks.stream().map(Book::getTitle).collect(Collectors.toList());
    }

}
