package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.User;
import org.softwire.training.bookish.models.page.LibraryPageModel;
import org.softwire.training.bookish.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


import java.util.List;

@Controller
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libraryService;
    private boolean ascending = true;

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


    @RequestMapping("/add-book")
    RedirectView addBook(@ModelAttribute Book book) {
        try{
            libraryService.addBook(book);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // redirect to author page to create an account
        }

        return new RedirectView("/library");
    }

    @RequestMapping("/delete-book")
    RedirectView deleteTechnology(@RequestParam int bookID) {
        try {
            libraryService.deleteBook(bookID);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return new RedirectView("/library");
    }


    @RequestMapping("/sort")
    ModelAndView sort(@RequestParam String column) {

        List<Book> allBooks = (ascending) ? libraryService.sort(column) : libraryService.sortReverse(column);
        ascending = !ascending;

        LibraryPageModel libraryPageModel = new LibraryPageModel();
        libraryPageModel.setBooks(allBooks);

        return new ModelAndView("library", "libraryModel", libraryPageModel);
    }

    @RequestMapping("/filterid")
    ModelAndView filterid(@RequestParam int id){
        List<Book> allBooks = libraryService.filterId(id);

        LibraryPageModel libraryPageModel = new LibraryPageModel();
        libraryPageModel.setBooks(allBooks);

        return new ModelAndView("library", "libraryModel", libraryPageModel);
    }

}
