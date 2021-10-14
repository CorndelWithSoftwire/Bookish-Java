package org.softwire.training.bookish.services;

import org.softwire.training.bookish.models.database.*;

import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.softwire.training.bookish.models.database.Author;
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

    public List<Author> sort(String column) {
        jdbi.installPlugin( new SqlObjectPlugin() ); // usually when connecting

        return jdbi.withExtension(
                AuthorDao.class, dao -> dao.sort(column));
    }

    public List<Author> sortReverse(String column) {
        jdbi.installPlugin( new SqlObjectPlugin() );

        return jdbi.withExtension(
                AuthorDao.class, dao -> dao.sortReverse(column));
    }

}
