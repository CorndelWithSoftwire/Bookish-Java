//package org.softwire.training.bookish.restService.services;
//
//import org.jdbi.v3.core.Jdbi;
//import org.softwire.training.bookish.execeptions.NoUserExeception;
//import org.softwire.training.bookish.models.database.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//
//import static org.softwire.training.bookish.connect.SqlConnect.createJdbiConnection;
//
//@Service
//public class ApplicationUserDetailsService implements UserDetailsService {
//    Jdbi jdbi = createJdbiConnection();
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = new User();
//        user.getUserFromDatabase(jdbi, username);
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPasshash(), Collections.emptyList());
//    }
//}
