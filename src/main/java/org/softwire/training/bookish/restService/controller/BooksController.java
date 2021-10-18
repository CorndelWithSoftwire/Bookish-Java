package org.softwire.training.bookish.restService.controller;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.BookDict;
import org.softwire.training.bookish.models.database.Books;
import org.softwire.training.bookish.restService.response.ErrorResponse;
import org.softwire.training.bookish.restService.response.Response;
import org.softwire.training.bookish.restService.response.SuccessfulBookCreation;
import org.softwire.training.bookish.restService.response.SuccessfulBookDeleteResponse;
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
        return new Book().getBookById(this.jdbi, id);
    }

    @PostMapping("book")
    Response createANewBook(@RequestBody Book newBook) {
        int id = (int) newBook.insertNewBook(this.jdbi);
        newBook.setBookID(id);
//        error when returning a new book object retrieved from the database.

        Optional<Integer> returnedId = newBook.getBookID();
        
        return returnedId.isEmpty()
                ? new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Something went wrong book couldn't be created")
                : new SuccessfulBookCreation(
                HttpStatus.CREATED.value(),
                String.format("Successfully Created new books %s", newBook.getTitle())
        );
    }


    @DeleteMapping("book/{id}")
    Response deleteABook(@PathVariable Integer id) {
        Book basicBookObj = new Book();

        // checks if there's a book in the db to even be deleted
        if (basicBookObj.getBookById(jdbi, id).getBookID().isEmpty()) {
            return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), String.format("No Book Found With Id:%s", id));
        }
        // if there is then delete
        basicBookObj.deleteBook(jdbi, id);
        Book findBook = basicBookObj.getBookById(jdbi, id);
        if (findBook.getBookID().isPresent()) {
            return new SuccessfulBookDeleteResponse(HttpStatus.OK.value(), String.format("Successfully deleted book %s", id));
        }
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Oops something went wrong on our side");
    }


}
