package org.softwire.training.bookish.commands;
import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.User;

import java.util.List;

public class GetLoans implements Command {

    @Override
    public void Execute(String input, Jdbi jdbi) {


        jdbi.withHandle(handle ->

                handle.createQuery("Select * FROM loans where ")
                        .mapToBean(User.class)
                        .list()

        ).forEach(v ->{System.out.println(v);});


    }
}
