package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.page.BooksPageModel;
import org.softwire.training.bookish.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

import static org.softwire.training.bookish.BookishWeb.currentUser;


@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookService bookService;

    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }


    @RequestMapping("")
    ModelAndView books(ModelMap model) {
        List<Book> allBooks = BookService.getAllBooks();

        BooksPageModel booksPageModel = new BooksPageModel();
        booksPageModel.setBooks(allBooks);

        return new ModelAndView("books", "model", booksPageModel);
    }

    @RequestMapping(value = "/add-book", method = RequestMethod.POST)
    RedirectView addBook(@ModelAttribute Book book){
        bookService.addBook(book);
        return new RedirectView("/books");
    }

    @ModelAttribute
    public void addAttributes(ModelMap model){
        model.addAttribute("currentUser", currentUser);
    }
}
