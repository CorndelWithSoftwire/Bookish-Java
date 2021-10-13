package org.softwire.training.bookish.services;

import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.softwire.training.bookish.models.database.Author;
import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.BookDao;
import org.softwire.training.bookish.models.database.LibraryDao;
import org.softwire.training.bookish.services.DatabaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService extends DatabaseService {
    public List<Author> searchForBookTitle(String userInputBookTitle) {
        jdbi.installPlugin((JdbiPlugin) new SqlObjectPlugin()); // usually when connecting

        List<Author> authors = jdbi.withExtension(
                BookDao.class, dao -> {
                    return dao.listAuthorAndBooks("%"+userInputBookTitle+"%");
                }
        );
        return authors;
    }
}
