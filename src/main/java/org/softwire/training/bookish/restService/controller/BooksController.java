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
import java.util.Optional;

import static org.softwire.training.bookish.connect.SqlConnect.createJdbiConnection;

@RestController
@RequestMapping("/api")
public class BooksController {
    Jdbi jdbi = createJdbiConnection();

    @GetMapping("books/{page}")
    List<BookDict> getAllBookNames(@PathVariable(value = "page", required = false) int page) {
        return new Books().getBooksList(this.jdbi, page);
    }

    @GetMapping("book/{id}")
    Book getBookById(@PathVariable(value = "id") int id) {
        return new Book().getBookById(this.jdbi, Optional.of(id));
    }

    @PostMapping("book")
    Response createANewBook(@RequestBody Book newBook) {

        newBook.insertBook(this.jdbi);
        Optional<Integer> id = newBook.getBookID();
        Book insertedBook = newBook.getBookById(this.jdbi, id);
        return insertedBook.getBookID().isEmpty()
                ? new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Something went wrong book couldn't be created")
                : new SuccessfulBookCreation(
                HttpStatus.CREATED.value(),
                String.format("Successfully Created new books %s", insertedBook.getTitle())
        );

    }

//    @DeleteMapping("book")
//    Response deleteANewbook(@RequestBody Book removeBook) {
////        removeBook.
//    }

//    @DeleteMapping("book/{id}")
//    Response deleteANewbook(@PathVariable Book removeBookByID) {
//
//    }


}
