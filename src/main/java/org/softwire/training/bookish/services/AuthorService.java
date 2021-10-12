package org.softwire.training.bookish.services;

import org.softwire.training.bookish.models.database.Author;

import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.softwire.training.bookish.models.database.Author;
import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.LibraryDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService extends DatabaseService{

    public List<Author> getAllAuthors(){
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM author")
                        .mapToBean(Author.class)
                        .list()
        );
    }

}
