package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Author;
import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.page.LibraryPageModel;
import org.softwire.training.bookish.services.LibraryService;
import org.softwire.training.bookish.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libraryService;
    private final SearchService searchService;
    private boolean ascending = true;

    @Autowired
    public LibraryController(LibraryService libraryService, SearchService searchService) {
        this.libraryService = libraryService;
        this.searchService = searchService;
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
    ModelAndView sort(@RequestParam String column, @RequestParam (name="authorId", required = false) Integer id,
                      @RequestParam (name="search", required = false) String searchString) {

        List<Book> allBooks = new ArrayList<>();
        LibraryPageModel libraryPageModel = new LibraryPageModel();
        libraryPageModel.setAuthorId(id);
        libraryPageModel.setSearchString(searchString);
        // for author books persistence
        if (! searchString.equals(null) & !searchString.equals("null")) {
            System.out.println("search string is not null");
            System.out.println(searchString.length());

            // copied code frm searchController
            try{
                List<Author> searchedBooksByAuthor = searchService.searchForBookTitle(searchString);
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
            }

            return new ModelAndView("library", "libraryModel", libraryPageModel);
            // end of copied code from search controller

        } else if (id == null || id == 0) {
            allBooks = (ascending) ? libraryService.sort(column) : libraryService.sortReverse(column);
            ascending = !ascending;
            System.out.println("yes");

        } else {
            allBooks = (ascending) ? libraryService.filterAndSort(column, id) : libraryService.filterAndSortReverse(column, id);
            ascending = !ascending;
            System.out.println("yes");

        }
        libraryPageModel.setBooks(allBooks);

        return new ModelAndView("library", "libraryModel", libraryPageModel);
    }

    @RequestMapping("/filterid")
    ModelAndView filterid(@RequestParam int id, String authorName){
        List<Book> allBooks = libraryService.filterId(id);

        LibraryPageModel libraryPageModel = new LibraryPageModel();
        libraryPageModel.setBooks(allBooks);
        libraryPageModel.setAuthorId(id);
        libraryPageModel.setAuthorName(authorName);

        return new ModelAndView("library", "libraryModel", libraryPageModel);
    }

    @RequestMapping("/find-author")
    @ResponseBody
    public String findAuthor(@RequestParam int id) {
        Author author;
        String authorName;

        if (id == 0) {
            authorName = "No author with this id";
        } else {
            author = libraryService.getAuthor(id);
            if (author == null) {
                authorName = "No author with this id";
            } else {
                authorName = author.getName();
            }

        }

        return authorName;
    }

}
