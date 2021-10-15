package org.softwire.training.bookish.restService.controller;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.execeptions.NoUserExeception;
import org.softwire.training.bookish.models.database.User;
import org.softwire.training.bookish.populateDB.PopulateDB;
import org.softwire.training.bookish.restService.models.LoginRequest;
import org.softwire.training.bookish.restService.models.passHashChecker;
import org.softwire.training.bookish.restService.models.registerRequest;
import org.softwire.training.bookish.restService.response.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class AuthController {

    @PostMapping("/register")
    Response registerNewUser(@RequestBody registerRequest userPayload) {
        System.out.println(userPayload);
        User newUser = new User();
        newUser.setUsername(userPayload.getUsername());
        newUser.setPasshashFromString(userPayload.getPassword());
        newUser.setEmail(userPayload.getEmail());
        newUser.setPhoneNumber(userPayload.getPhone());
        Jdbi jdbi = PopulateDB.createJdbiConnection();
        try {
            newUser.getUserFromDatabase(jdbi, newUser.getUsername());
        } catch (NoUserExeception ex) {
            newUser.insertUserToDatabase(jdbi);
            User foundUser = new User();
            try {
                foundUser.getUserFromDatabase(jdbi, userPayload.getUsername());
            } catch (NoUserExeception exeception) {
                return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exeception.getLocalizedMessage());
            }
        }
        return new UserSuccessResponse(HttpStatus.CREATED.value(), "Successfully Created new User");
    }

    //    @ResponseBody
    @PostMapping("/login")
    public @ResponseBody
    Response logUserIn(@RequestBody LoginRequest userPayload) {
        /*
        Todo: Create a web token when the correct user have been found in the db

        log user in by producing a web token that they can store on the frontend
        or
         */
        // check if the username and password are the same, if so return json web token if not then an error-
        Jdbi jdbi = PopulateDB.createJdbiConnection();
        User userFound = new User();
        userFound.setUsername(userPayload.getUsername());
        userFound.setPasshashFromString(userPayload.getPassword());

        try {
            userFound.getUserFromDatabase(jdbi, userPayload.getUsername());
            passHashChecker check = new passHashChecker(userPayload.getPassword());
            if (!check.equals(userFound.getPasshash())) {
                throw new NoUserExeception("password found doesn't match");
            }
        } catch (NoUserExeception e) {
            e.printStackTrace();
            return new FailedLoginResponse(HttpStatus.NOT_FOUND.value(), "This User doesn't exist");
        }

        if (userFound.getUsername().equals(userPayload.getUsername())) {
            return new SuccessfulLoginResponse(HttpStatus.OK.value(), "ebc2b8fcf79ac184c8c5dd112ea2ce64912023ab6e4de8d55f18494b0504f2d3");
        }
        return new ErrorResponse(500, " not implemented");
    }


}
