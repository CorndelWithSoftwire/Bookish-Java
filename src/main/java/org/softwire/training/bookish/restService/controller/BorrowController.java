package org.softwire.training.bookish.restService.controller;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.execeptions.NoBorrowsException;
import org.softwire.training.bookish.execeptions.NoUserExeception;
import org.softwire.training.bookish.models.database.Borrow;
import org.softwire.training.bookish.models.database.Copies;
import org.softwire.training.bookish.models.database.Copy;
import org.softwire.training.bookish.models.database.User;
import org.softwire.training.bookish.populateDB.PopulateDB;
import org.softwire.training.bookish.restService.response.BorrowSuccessResponse;
import org.softwire.training.bookish.restService.response.BorrowsListResponse;
import org.softwire.training.bookish.restService.response.ErrorResponse;
import org.softwire.training.bookish.restService.response.Response;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.softwire.training.bookish.connect.SqlConnect.createJdbiConnection;

@RestController
public class BorrowController {
    Jdbi jdbi = createJdbiConnection();

    @PostMapping("/borrow")
    Response borrowBook(@RequestBody Borrow newBorrow) {
        Calendar cal = Calendar.getInstance();
        Date today = new Date();
        Copies copies = new Copies();
        copies.getCopiesFromDb(jdbi, Integer.parseInt(newBorrow.getBookId()));
        int length = copies.getCopies().size();
        Copy singleCopy = copies.getCopies().get(length - 1);
        // adds 28 dasys from the current date.
        cal.add(Calendar.DATE, 28);
        newBorrow.setBorrowCopyId(singleCopy.getCopyId());
        newBorrow.setCheckOutDate(today);
        newBorrow.setDueDate(cal.getTime());
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
