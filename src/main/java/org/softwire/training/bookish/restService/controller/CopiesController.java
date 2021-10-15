package org.softwire.training.bookish.restService.controller;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.models.database.Copies;
import org.softwire.training.bookish.models.database.Copy;
import org.softwire.training.bookish.populateDB.PopulateDB;
import org.softwire.training.bookish.restService.response.CopiesResponse;
import org.softwire.training.bookish.restService.response.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.softwire.training.bookish.connect.SqlConnect.createJdbiConnection;

@RestController
public class CopiesController {
    static private final Jdbi jdbi = createJdbiConnection();

    @GetMapping("/copy/{id}")
    Response getCopiesForId(@PathVariable("id") int bookId) {
        Copies copies = new Copies();
        copies.getCopiesFromDb(jdbi, bookId);
        return new CopiesResponse(200, copies.getCopies());
    }


}
