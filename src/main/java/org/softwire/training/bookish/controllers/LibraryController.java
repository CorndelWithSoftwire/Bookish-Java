package org.softwire.training.bookish.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/library")
public class LibraryController {

    @RequestMapping("")
    ModelAndView library() {

        return new ModelAndView("library");
    }
}
