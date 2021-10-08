package org.softwire.training.bookish.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("")
    ModelAndView user() {

        return new ModelAndView("user");
    }
}