package org.softwire.training.bookish.services;

import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.BookDao;
import org.springframework.stereotype.Service;

@Service
public class BookSplashService extends DatabaseService {

    public Book getBook(int id) {
        jdbi.installPlugin(new SqlObjectPlugin()); // usually when connecting

        return jdbi.withExtension(
                BookDao.class, dao -> dao.filterId(id));
    }
}
