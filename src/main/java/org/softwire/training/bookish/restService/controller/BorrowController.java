package org.softwire.training.bookish.restService.controller;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.execeptions.NoBorrowsException;
import org.softwire.training.bookish.execeptions.NoUserExeception;
import org.softwire.training.bookish.models.database.Borrow;
import org.softwire.training.bookish.models.database.User;
import org.softwire.training.bookish.populateDB.PopulateDB;
import org.softwire.training.bookish.restService.response.BorrowSuccessResponse;
import org.softwire.training.bookish.restService.response.BorrowsListResponse;
import org.softwire.training.bookish.restService.response.ErrorResponse;
import org.softwire.training.bookish.restService.response.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.softwire.training.bookish.connect.SqlConnect.createJdbiConnection;

@RestController
public class BorrowController {
    Jdbi jdbi = createJdbiConnection();

    @PostMapping("/borrow")
    Response borrowBook(@RequestBody Borrow newBorrow) {
        int borrowId = newBorrow.insertIntoDatabase(jdbi);
        return new BorrowSuccessResponse(200, borrowId);
    }

    @GetMapping("/borrow/byUser/{username}")
    Response getBookBorrow(@PathVariable("username") String username) {
        Borrow borrow = new Borrow();
        List<Borrow> borrows;
        try {
            borrows = borrow.queryByUsername(jdbi, username);
        } catch (NoBorrowsException ex) {
            return new ErrorResponse(500, ex.getLocalizedMessage());
        }
        return new BorrowsListResponse(200, borrows);
    }

    @GetMapping("/borrow/byId/{id}")
    Response getBorrowById(@PathVariable("id") int id) {
       List<Borrow> borrows;
       try {
           borrows = new Borrow().queryById(jdbi, id);
       } catch (NoBorrowsException e) {
           return new ErrorResponse(400, e.getLocalizedMessage());
       }
       return new BorrowsListResponse(200, borrows);
    }
}
