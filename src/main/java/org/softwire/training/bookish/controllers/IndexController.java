package org.softwire.training.bookish.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @RequestMapping("/")
    ModelAndView home() {
        return new ModelAndView("index");
    }

    @GetMapping("/greeting")
    public String greeting() {
        return "greeting";
    }

//    @GetMapping("/about")
//    public String about() {
//        return "about";
//    }

}
