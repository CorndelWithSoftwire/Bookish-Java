package org.softwire.training.bookish.restService.controller;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.execeptions.NoUserExeception;
import org.softwire.training.bookish.models.database.Librarian;
import org.softwire.training.bookish.models.database.User;
import org.softwire.training.bookish.populateDB.PopulateDB;
import org.softwire.training.bookish.restService.response.ErrorResponse;
import org.softwire.training.bookish.restService.response.Response;
import org.softwire.training.bookish.restService.response.UserGetSuccessResponse;
import org.softwire.training.bookish.restService.response.UserSuccessResponse;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @PostMapping("/user/promote/{userId}")
    public Response promoteUser(@PathVariable(value="userId")String userId) {
        Jdbi jdbi = PopulateDB.createJdbiConnection();
        User userToPromote = new User();
        try {
            userToPromote.getUserFromDatabase(jdbi, userId);
        } catch (NoUserExeception ex) {
            return new ErrorResponse(400, ex.getLocalizedMessage());
        }
        Librarian newLibrarian = new Librarian();
        newLibrarian.setUsername(userToPromote.getUsername());
        newLibrarian.insertLibrarianIntoDb(jdbi);
        return new UserSuccessResponse(200, newLibrarian.getUsername());
    }

    @PostMapping("/user/demote/{userId}")
    public Response demoteUser(@PathVariable("userId") String userId) {
        Jdbi jdbi = PopulateDB.createJdbiConnection();
        User userToDemote = new User();
        try {
            userToDemote.getUserFromDatabase(jdbi, userId);
        } catch (NoUserExeception noUserExeception) {
            return new ErrorResponse(400, noUserExeception.getLocalizedMessage());
        }
        return new UserSuccessResponse(200, userId);
    }

    @DeleteMapping("/user/delete/{userId}")
    public Response deleteUser(@PathVariable("userId") String userId) {
        Jdbi jdbi = PopulateDB.createJdbiConnection();
        User userToDelete = new User();
        try {
            userToDelete.getUserFromDatabase(jdbi, userId);
        } catch (NoUserExeception noUserExeception) {
            return new ErrorResponse(400, noUserExeception.getLocalizedMessage());
        }
        return new UserSuccessResponse(200, userId);
    }

    @GetMapping("/user/{id}")
    public Response getUser(@PathVariable("id") String userId) {
        Jdbi jdbi = PopulateDB.createJdbiConnection();
        User user = new User();
        try {
            user.getUserFromDatabase(jdbi, userId);
        } catch (NoUserExeception ex) {
            return new ErrorResponse(400, ex.getLocalizedMessage());
        }
        return new UserGetSuccessResponse(200, user);
    }
}
