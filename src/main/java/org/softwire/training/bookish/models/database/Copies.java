package org.softwire.training.bookish.models.database;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.models.dao.CopyDao;

import java.util.List;

public class Copies {
    private List<Copy> copies;

    public List<Copy> getCopies() {
        return copies;
    }

    public void setCopies(List<Copy> copies) {
        this.copies = copies;
    }

    public void getCopiesFromDb(Jdbi jdbi, int bookId) {
        this.copies = jdbi.withExtension(CopyDao.class, dao -> dao.getBookCopies(bookId));
    }
}
