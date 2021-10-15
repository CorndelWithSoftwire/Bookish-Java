package org.softwire.training.bookish.models.database;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.models.dao.AuthorDao;

import java.util.Optional;

public class Author {
    private  String authorName;
    private Optional<Integer> authorId;


    public Author(String authorName) {
        this.authorName = authorName;
    }

    public Author(){};

    public String getAuthorName() {
        return authorName;
    }

    public Optional<Integer> getAuthorId() {
        return authorId;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + authorName + '\'' +
                ", id=" + authorId +
                '}';
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setAuthorId(int authorId) {
        this.authorId = Optional.of(authorId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        return authorName.equals(author.authorName);
    }

    @Override
    public int hashCode() {
        return authorName.hashCode();
    }

    public int insertIntoDb(Jdbi jdbi) {
       return jdbi.withExtension(AuthorDao.class, dao -> dao.insertAuthors(this.getAuthorName()));
    }
}


