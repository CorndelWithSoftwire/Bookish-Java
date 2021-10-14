package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.page.BookSplashPageModel;
import org.softwire.training.bookish.services.BookSplashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/book-splash")
public class BookSplashController {

    private final BookSplashService bookSplashService;

    @Autowired
    public BookSplashController(BookSplashService bookSplashService) {
        this.bookSplashService = bookSplashService;
    }

    @RequestMapping("")
    ModelAndView bookSplash(@RequestParam int id) {
        Book book = bookSplashService.getBook(id);

        BookSplashPageModel bookSplashPageModel = new BookSplashPageModel();
        bookSplashPageModel.setBook(book);

        return new ModelAndView("book", "bookSplashModel", bookSplashPageModel);
    }
}

