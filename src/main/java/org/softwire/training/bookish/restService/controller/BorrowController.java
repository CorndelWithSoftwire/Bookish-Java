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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class BorrowController {
    @PostMapping("/borrow")
    Response borrowBook(@RequestBody Borrow newBorrow) {
        Jdbi jdbi = PopulateDB.createJdbiConnection();
        newBorrow.insertIntoDatabase(jdbi);
        return new BorrowSuccessResponse(200, 234324);
    }

    @GetMapping("/borrow/byUser/{username}")
    Response getBookBorrow(@PathVariable("username") String username) {
        Jdbi jdbi = PopulateDB.createJdbiConnection();
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
       Jdbi jdbi = PopulateDB.createJdbiConnection();
       List<Borrow> borrows;
       try {
           borrows = new Borrow().queryById(jdbi, id);
       } catch (NoBorrowsException e) {
           return new ErrorResponse(400, e.getLocalizedMessage());
       }
       return new BorrowsListResponse(200, borrows);
    }
}
