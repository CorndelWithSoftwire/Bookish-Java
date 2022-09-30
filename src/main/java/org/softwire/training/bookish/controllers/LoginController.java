package org.softwire.training.bookish.controllers;


import org.softwire.training.bookish.models.database.User;
import org.softwire.training.bookish.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Objects;

import static org.softwire.training.bookish.BookishWeb.*;

@Controller
@ControllerAdvice
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("")
    ModelAndView login(ModelMap model){
        return new ModelAndView("Login");
    }

    @RequestMapping(value ="/login-check", method = RequestMethod.POST)
    RedirectView loginCheck(@RequestParam("Name") String name, @RequestParam("Password") String password){
        List<User> userList = UserService.getAllUsers();
        for (User user : userList){
            if (user.getName().equals(name) && user.getPassword().equals(password)){
                String type = user.getType();
                if (Objects.equals(type, "Librarian")){
                    setCurrentUser(user.getName());
                    return new RedirectView("/books");
                } else {
                    setCurrentUser(user.getName());
                    return new RedirectView("/books");
                }
            } else if (user.getName().equals(name) && !user.getPassword().equals(password)){
                return new RedirectView("/login");
            } else {
                return new RedirectView("/login");
            }
        }
        return new RedirectView("/index");
    }

    @RequestMapping(value="/add-user", method = RequestMethod.POST)
    RedirectView addUser(@RequestParam("Name") String name, @RequestParam("Password") String password){
        userService.addUser(name, password, "User", 0);
        return new RedirectView("/books");
    }

    @ModelAttribute
    public void addAttributes(ModelMap model){
        model.addAttribute("currentUser", currentUser);
    }
}
