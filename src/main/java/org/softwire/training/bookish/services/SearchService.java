package org.softwire.training.bookish.services;

import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.softwire.training.bookish.models.database.Author;
import org.softwire.training.bookish.models.database.BookDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService extends DatabaseService {
    public List<Author> searchForBookTitle(String userInput) {
        jdbi.installPlugin(new SqlObjectPlugin()); // usually when connecting

        return jdbi.withExtension(
                BookDao.class, dao -> dao.listAuthorAndBooks("%"+userInput+"%"));
    }
}
