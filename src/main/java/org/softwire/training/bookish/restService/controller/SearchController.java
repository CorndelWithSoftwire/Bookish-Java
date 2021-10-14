package org.softwire.training.bookish.restService.controller;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.populateDB.PopulateDB;
import org.softwire.training.bookish.restService.response.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

//public class SearchController {
//    @PostMapping("/search")
//    Response search(@RequestParam(value = "author", required = false) String author,
//                    @RequestParam(value = "title", required = false) String title,
//                    @RequestParam(value = "isbn", required = false) String isbn) {
//        Jdbi jdbi = PopulateDB.createJdbiConnection();
//
//    }
//}
