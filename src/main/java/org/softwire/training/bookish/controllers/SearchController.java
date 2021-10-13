package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Author;

import org.softwire.training.bookish.models.page.SearchPageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.softwire.training.bookish.services.SearchService;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping("")
    ModelAndView librarySearch() {
        List<Author> searchedBooks = searchService.searchForBookTitle("Potter");

        SearchPageModel searchPageModel = new SearchPageModel();
        searchPageModel.setBooks(searchedBooks);

        return new ModelAndView("search", "searchModelModel", searchPageModel);
    }

}
