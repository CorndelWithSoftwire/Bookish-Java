package org.softwire.training.bookish.restService.controller;

import org.jdbi.v3.core.Jdbi;
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

    @GetMapping("books/{page}")
    List<String> getAllBookNames(@PathVariable(value = "page", required = false) int page) {
        Jdbi jdbi = PopulateDB.createJdbiConnection();
        return new Books().getBooksList(jdbi, page);
    }


}
