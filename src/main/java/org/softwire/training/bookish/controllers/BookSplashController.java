package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.page.BookSplashPageModel;
import org.softwire.training.bookish.services.BookSplashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.*;
import org.softwire.training.bookish.models.database.Author;

@Controller
@RequestMapping("/book-splash")
public class BookSplashController {

    private final BookSplashService bookSplashService;

    @Autowired
    public BookSplashController(BookSplashService bookSplashService) {
        this.bookSplashService = bookSplashService;
    }

    @RequestMapping("/filterid")
    ModelAndView bookSplash(@RequestParam int id) {
        Book book = bookSplashService.getBook(id);

        BookSplashPageModel bookSplashPageModel = new BookSplashPageModel();
        bookSplashPageModel.setBook(book);

        return new ModelAndView("book-splash", "bookSplashModel", bookSplashPageModel);
    }

    @RequestMapping("/edit-book")
    RedirectView editBook(@ModelAttribute Book book) {
        try{
            bookSplashService.editBook(book);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new RedirectView("/book-splash/filterid?id="+book.getId());
    }

    @RequestMapping("/find-author")
    @ResponseBody
    public String findAuthor(@RequestParam int id) {
        Author author;
        String authorName;

        if (id == 0) {
            authorName = "No author with this id";
        } else {
            author = bookSplashService.getAuthor(id);
            if (author == null) {
                authorName = "No author with this id";
            } else {
                authorName = author.getName();
            }

        }

        return authorName;
    }
}
