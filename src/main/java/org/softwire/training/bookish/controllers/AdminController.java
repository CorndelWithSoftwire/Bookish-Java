package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.User;
import org.softwire.training.bookish.models.page.AdminPageModel;
import org.softwire.training.bookish.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private boolean ascending = true;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("")
    ModelAndView admin() {
        List<User> allUsers = userService.getAllUsers();

        AdminPageModel adminPageModel = new AdminPageModel();
        adminPageModel.setUsers(allUsers);

        return new ModelAndView("admin", "adminModel", adminPageModel);
    }

    @RequestMapping("/sort")
    ModelAndView sort(@RequestParam String column) {

        List<User> allUsers = (ascending) ? userService.sort(column) : userService.sortReverse(column);
        ascending = !ascending;

        AdminPageModel adminPageModel = new AdminPageModel();
        adminPageModel.setUsers(allUsers);

        return new ModelAndView("admin", "adminModel", adminPageModel);
    }

    @RequestMapping("/add-user")
    RedirectView addUser(@ModelAttribute User user) {

        userService.addUser(user);

        return new RedirectView("/admin");
    }

    @RequestMapping("/delete-user")
    RedirectView deleteUser(@RequestParam int userId) {

        userService.deleteUser(userId);

        return new RedirectView("/admin");
    }
}
