package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.User;
import org.softwire.training.bookish.models.page.AboutPageModel;
import org.softwire.training.bookish.models.page.UserPageModel;
import org.softwire.training.bookish.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("")
    ModelAndView user() {
        UserPageModel userPageModel = new UserPageModel();


        return new ModelAndView("user", "userModel", userPageModel);
    }

    @RequestMapping("/userid")
    ModelAndView userid(@RequestParam int id,
                        @RequestParam String forename,
                        @RequestParam String surname,
                        @RequestParam (name="bookID", required = false) Integer book_id,
                        @RequestParam (name="return", required = false) String returnOption) {

        if (returnOption != null) {
            if (returnOption.toLowerCase().equals("true")) {
                    try {
                        userService.returnBook(book_id, id);
                    } catch (Exception e) {
                        e.getMessage();
                    }
            }
        } else if (book_id != 0 & book_id != null) {
            try {
                userService.borrow(book_id, id);
            } catch (Exception e) {
                e.getMessage();
            }

        }



        List<Book> bookList = userService.getBorrowList(id);
        UserPageModel userPageModel = new UserPageModel();
        userPageModel.setBooks(bookList);

        User user = new User();
        user.setId(id);
        user.setForename(forename);
        user.setSurname(surname);
        userPageModel.setUser(user);

        List<Book> availableBooksToBorrow = userService.getAvailableBooksToBorrow();
        userPageModel.setAvailableBooksToBorrow(availableBooksToBorrow);

        return new ModelAndView("user", "userModel", userPageModel);
    }
}