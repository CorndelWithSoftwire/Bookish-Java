package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Author;
import org.softwire.training.bookish.models.page.AuthorPageModel;
import org.softwire.training.bookish.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/author")
public class AuthorController extends ExceptionController {

    private final AuthorService authorService;
    private boolean ascending = true;

    @Autowired
    public AuthorController(AuthorService authorService){this.authorService = authorService;}

    @RequestMapping("")
    ModelAndView author() {
        List<Author> allAuthors = authorService.getAllAuthors();

        AuthorPageModel authorPageModel = new AuthorPageModel();
        authorPageModel.setAuthors(allAuthors);

        return new ModelAndView("author", "authorModel", authorPageModel);
    }

    @RequestMapping("/sort")
    ModelAndView sort(@RequestParam String column) {

        List<Author> allAuthors = (ascending) ? authorService.sort(column) : authorService.sortReverse(column);
        ascending = !ascending;

        AuthorPageModel authorPageModel = new AuthorPageModel();
        authorPageModel.setAuthors(allAuthors);

        return new ModelAndView("author", "authorModel", authorPageModel);
    }

    @RequestMapping("/add-author")
    RedirectView addAuthor(Author author) {
        try {
            authorService.addAuthor(author);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new RedirectView("/author");
    }

//    @RequestMapping("/edit-author")
//    RedirectView editAuthor(@ModelAttribute Author author) {
//        try {
//            authorService.editAuthor(author);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return new RedirectView("/library");
//    }
}
