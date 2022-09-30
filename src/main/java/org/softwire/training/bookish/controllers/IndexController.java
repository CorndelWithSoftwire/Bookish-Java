package org.softwire.training.bookish.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.softwire.training.bookish.BookishWeb.currentUser;

@Controller
public class IndexController {

    @RequestMapping({"/", "Error"})
    ModelAndView home(ModelMap model) {
        return new ModelAndView("index");
    }

    @ModelAttribute
    public void addAttributes(ModelMap model){
        model.addAttribute("currentUser", currentUser);
    }

}
