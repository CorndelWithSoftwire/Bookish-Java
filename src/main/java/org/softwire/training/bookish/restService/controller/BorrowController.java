package org.softwire.training.bookish.restService.controller;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.models.database.Borrow;
import org.softwire.training.bookish.populateDB.PopulateDB;
import org.softwire.training.bookish.restService.response.BorrowSuccessResponse;
import org.softwire.training.bookish.restService.response.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//public class BorrowController {
//    @PostMapping("/borrow")
//    Response borrowBook(@RequestBody Borrow newBorrow) {
//        Jdbi jdbi = PopulateDB.createJdbiConnection();
//        newBorrow.insertIntoDatabase(jdbi);
//        return new BorrowSuccessResponse(200, 234324);
//    }
//
//}
