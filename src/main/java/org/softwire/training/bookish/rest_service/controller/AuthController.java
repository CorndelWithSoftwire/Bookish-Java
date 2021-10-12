package org.softwire.training.bookish.rest_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    @GetMapping("/register")
    String registerNewUser() {
        // Todo: create a user obj and save into DB
        return "End Point to register";
    }

    @GetMapping("/login")
    String logUserIn() {
        /*
        Todo: Create a web token when the correct user have been found in the db
        log user in by producing a web token that they can store on the frontend
        or
         */
        return "End Point to login";
    }


}
