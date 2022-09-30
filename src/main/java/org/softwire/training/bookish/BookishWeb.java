package org.softwire.training.bookish;

import org.softwire.training.bookish.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point to the Bookish Application
 */
@SpringBootApplication
public class BookishWeb {
    public static String currentUser;
    public static void main(String[] args) {
        SpringApplication.run(BookishWeb.class, args);
    }

    public static void setCurrentUser(String userName){
        currentUser = userName;
    }

    public static String getCurrentUser(){return currentUser;}
}
