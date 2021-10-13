package org.softwire.training.bookish.restService.controller;

import org.softwire.training.bookish.models.database.User;
import org.softwire.training.bookish.restService.models.JsonWebToken;
import org.softwire.training.bookish.restService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    UserService userService;

    @GetMapping("/register")
    User registerNewUser() {
        // Todo: create a user obj and save into DB
        String username = "mbpp";
        String passhash = "VeryBadPractice";
        String email = "mbpp@amex.com";
        String phoneNumber = "7854 744 992";
        return new User(username, passhash, email, phoneNumber);
    }

    //    @ResponseBody
    @PostMapping("/login")
    JsonWebToken logUserIn(@RequestBody User userPayload) {

        /*
        Todo: Create a web token when the correct user have been found in the db
        log user in by producing a web token that they can store on the frontend
        or
         */
        return new JsonWebToken("ebc2b8fcf79ac184c8c5dd112ea2ce64912023ab6e4de8d55f18494b0504f2d3", userPayload);
    }

    @PostMapping("/create-user")
    User makeNewUser(@RequestBody User user) {
        return userService.save(user);
    }


}
