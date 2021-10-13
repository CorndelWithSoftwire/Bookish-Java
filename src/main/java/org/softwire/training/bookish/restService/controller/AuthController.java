package org.softwire.training.bookish.restService.controller;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.execeptions.NoUserExeception;
import org.softwire.training.bookish.models.database.User;
import org.softwire.training.bookish.populateDB.PopulateDB;
import org.softwire.training.bookish.restService.models.JsonWebToken;
import org.softwire.training.bookish.restService.response.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @PostMapping("/register")
    @ResponseBody
    Response registerNewUser(@RequestBody User userPayload) {
        Jdbi jdbi = PopulateDB.createJdbiConnection();
        try {
            User checkToSeeIfUserExist = userPayload.getUserFromDatabase(jdbi, userPayload.getUsername());
        } catch (NoUserExeception ex) {
            userPayload.insertUserToDatabase(jdbi);
            try {
                User foundUser = userPayload.getUserFromDatabase(jdbi, userPayload.getUsername());
            } catch (NoUserExeception exeception) {
                return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Unable to register user");
            }
        }
        return new UserSuccessResponse(HttpStatus.CREATED.value(), "Successfully Created new User");
    }

    //    @ResponseBody
    @PostMapping("/login")
    public @ResponseBody
    Response logUserIn(@RequestBody User userPayload) {
        /*
        Todo: Create a web token when the correct user have been found in the db

        log user in by producing a web token that they can store on the frontend
        or
         */
        // check if the username and password are the same, if so return json web token if not then an error-
        Jdbi jdbi = PopulateDB.createJdbiConnection();
        User userFound = null;
        try {
            userFound = userPayload.getUserFromDatabase(jdbi, userPayload.getUsername());
        } catch (NoUserExeception e) {
            e.printStackTrace();
        }
        if (userFound != null && userFound.getUsername().equals(userPayload.getUsername())) {

            return new SuccessfulLoginResponse(HttpStatus.OK.value(),"ebc2b8fcf79ac184c8c5dd112ea2ce64912023ab6e4de8d55f18494b0504f2d3");
        }
        return new FailedLoginResponse(HttpStatus.NOT_FOUND.value(),"This User doesn't exist");
    }


}
