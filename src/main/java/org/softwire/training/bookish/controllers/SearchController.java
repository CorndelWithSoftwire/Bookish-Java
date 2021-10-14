package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Author;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.page.LibraryPageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.softwire.training.bookish.services.SearchService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/library/search")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping("")
    ModelAndView searchBook(@RequestParam String search) {
        LibraryPageModel libraryPageModel = new LibraryPageModel();

        try{
            List<Author> searchedBooksByAuthor = searchService.searchForBookTitle(search);
            List<Book> searchedBookList = new ArrayList<>();
            for (Author author : searchedBooksByAuthor) {
                for (Book book : author.getWrittenBookList()) {
                    searchedBookList.add(new Book(book.getId(), book.getTitle(), book.getIsbn(), book.getPublishedDate(),
                            book.getPublisher(), book.getGenre(), book.getNumberOfCopies(), book.getAuthorId(), author.getName()));
                }
            }
            libraryPageModel.setBooks(searchedBookList);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            // redirect to author page to create an account
        }

        return new ModelAndView("library", "libraryModel", libraryPageModel);
    }

}
