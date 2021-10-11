package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.page.LibraryPageModel;
import org.softwire.training.bookish.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


import java.util.List;

@Controller
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @RequestMapping("")
    ModelAndView library() {
        List<Book> allBooks = libraryService.getAllBooks();

        LibraryPageModel libraryPageModel = new LibraryPageModel();
        libraryPageModel.setBooks(allBooks);

        return new ModelAndView("library", "libraryModel", libraryPageModel);
    }

    @RequestMapping("/sort-by-title")
    ModelAndView sortByTitle() {
        List<Book> allBooks = libraryService.sortByTitle();

        LibraryPageModel libraryPageModel = new LibraryPageModel();
        libraryPageModel.setBooks(allBooks);

        return new ModelAndView("library", "libraryModel", libraryPageModel);
    }
    @RequestMapping("/sort-by-isbn")
    ModelAndView sortByISBN() {
        List<Book> allBooks = libraryService.sortByISBN();

        LibraryPageModel libraryPageModel = new LibraryPageModel();
        libraryPageModel.setBooks(allBooks);

        return new ModelAndView("library", "libraryModel", libraryPageModel);
    }

    @RequestMapping("/sort-by-published-date")
    ModelAndView sortByPublishedDate() {
        List<Book> allBooks = libraryService.sortByPublishedDate();

        LibraryPageModel libraryPageModel = new LibraryPageModel();
        libraryPageModel.setBooks(allBooks);

        return new ModelAndView("library", "libraryModel", libraryPageModel);
    }

    @RequestMapping("/sort-by-publisher")
    ModelAndView sortByPublisher() {
        List<Book> allBooks = libraryService.sortByPublisher();

        LibraryPageModel libraryPageModel = new LibraryPageModel();
        libraryPageModel.setBooks(allBooks);

        return new ModelAndView("library", "libraryModel", libraryPageModel);
    }

    @RequestMapping("/sort-by-genre")
    ModelAndView sortByGenre() {
        List<Book> allBooks = libraryService.sortByGenre();

        LibraryPageModel libraryPageModel = new LibraryPageModel();
        libraryPageModel.setBooks(allBooks);

        return new ModelAndView("library", "libraryModel", libraryPageModel);
    }

    @RequestMapping("/sort-by-number-of-copies")
    ModelAndView sortByNumberOfCopies() {
        List<Book> allBooks = libraryService.sortByNumberOfCopies();

        LibraryPageModel libraryPageModel = new LibraryPageModel();
        libraryPageModel.setBooks(allBooks);

        return new ModelAndView("library", "libraryModel", libraryPageModel);
    }

    @RequestMapping("/sort-by-author-id")
    ModelAndView sortByAuthorID() {
        List<Book> allBooks = libraryService.sortByAuthorID();

        LibraryPageModel libraryPageModel = new LibraryPageModel();
        libraryPageModel.setBooks(allBooks);

        return new ModelAndView("library", "libraryModel", libraryPageModel);
    }


}
