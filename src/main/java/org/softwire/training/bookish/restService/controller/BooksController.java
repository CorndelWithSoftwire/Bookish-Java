package org.softwire.training.bookish.restService.controller;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.BookDict;
import org.softwire.training.bookish.models.database.Books;
import org.softwire.training.bookish.populateDB.PopulateDB;
import org.softwire.training.bookish.restService.response.ErrorResponse;
import org.softwire.training.bookish.restService.response.Response;
import org.softwire.training.bookish.restService.response.SuccessfulBookCreation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("book")
    Response createANewBook(@RequestBody Book newBook) {

        newBook.insertBook(this.jdbi);
        int id = newBook.getBookID();
        Book insertedBook = newBook.getBookById(this.jdbi, id);
        return insertedBook.getBookID() == null ?
                new SuccessfulBookCreation(
                        HttpStatus.CREATED.value(),
                        String.format("Successfully Created new books %s", insertedBook.getTitle())
                ) :
                new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Something went wrong book couldn't be created");
    }


}
