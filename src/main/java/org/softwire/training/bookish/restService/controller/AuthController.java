package org.softwire.training.bookish.restService.controller;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.execeptions.NoUserExeception;
import org.softwire.training.bookish.models.database.User;
import org.softwire.training.bookish.populateDB.PopulateDB;
import org.softwire.training.bookish.restService.models.JsonWebToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthController {

    @PostMapping("/register")
    ResponseEntity<String> registerNewUser(@RequestBody User userPayload) {
        Jdbi jdbi = PopulateDB.createJdbiConnection();
        try {
            User checkToSeeIfUserExist = userPayload.getUserFromDatabase(jdbi, userPayload.getUsername());
        } catch (NoUserExeception ex) {
            userPayload.insertUserToDatabase(jdbi);
            try {
                User foundUser = userPayload.getUserFromDatabase(jdbi, userPayload.getUsername());
            } catch {
             e
            }
            if (foundUser.getUsername().equals(userPayload.getUsername())) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Successfully Created new User");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to register user");


    }

    //    @ResponseBody
    @PostMapping("/login")
    ResponseEntity<String> logUserIn(@RequestBody User userPayload) {
        /*
        Todo: Create a web token when the correct user have been found in the db

        log user in by producing a web token that they can store on the frontend
        or
         */
        // check if the username and password are the same, if so return json web token if not then an error-
        Jdbi jdbi = PopulateDB.createJdbiConnection();
        List<User> userFound = userPayload.getUserFromDatabase(jdbi, userPayload.getUsername());
        if (userFound.get(0).getUsername().equals(userPayload.getUsername())) {
            JsonWebToken jwt = new JsonWebToken("ebc2b8fcf79ac184c8c5dd112ea2ce64912023ab6e4de8d55f18494b0504f2d3", userPayload);
            return ResponseEntity.status(HttpStatus.OK).body(jwt.getToken());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This User doesn't exist");
    }


}
