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
        List<Author> authorList = jdbi.withExtension(
                AuthorDao.class, dao -> {
                    return dao.sort(column);
                });

        return authorList;
    }

    public List<Author> sortReverse(String column) {
        jdbi.installPlugin( new SqlObjectPlugin() );
        List<Author> authorList = jdbi.withExtension(
                AuthorDao.class, dao -> {
                    return dao.sortReverse(column);

                });

        return authorList;
    }

}
