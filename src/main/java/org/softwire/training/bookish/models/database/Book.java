package org.softwire.training.bookish.models.database;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.models.dao.BookDao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class Book {
    Optional<Integer> BookId;
    String Title;
    String Authors;
    Date Created_at;
    Date Updated_at;
    String Slug;
    String ISBN;
    String Subtitle;
    String Subjects;
    String Cover_photo_url;


    public Book() {
    }

    public String getAuthors() {
        return Authors;
    }

    public void setAuthors(String authors) {
        Authors = authors;
    }

    @JsonIgnore
    public Optional<Integer> getBookId() {
        return BookId;
    }

    public void setBookId(Integer bookId) {
        BookId = Optional.ofNullable(bookId);
    }

    public void setBookId(Optional<Integer> bookId) {
        this.BookId = bookId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Date getCreated_at() {
        return Created_at;
    }

    public void setCreated_at(Date created_at) {
        Created_at = created_at;
    }

    public void setCreated_at(String created_at) {
        Created_at = createDate(created_at);
    }

    public Date getUpdated_at() {
        return Updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        Updated_at = updated_at;
    }

    public void setUpdated_at(String updated_at) {
        Updated_at = createDate(updated_at);
    }

    public String getSlug() {
        return Slug;
    }

    public void setSlug(String slug) {
        Slug = slug;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getSubtitle() {
        return Subtitle;
    }

    public void setSubtitle(String subtitle) {
        Subtitle = subtitle;
    }

    public String getSubjects() {
        return Subjects;
    }

    public void setSubjects(String subjects) {
        Subjects = subjects;
    }

    public String getCover_photo_url() {
        return Cover_photo_url;
    }

    public void setCover_photo_url(String cover_photo_url) {
        Cover_photo_url = cover_photo_url;
    }

    public int insertBook(Jdbi jdbi) {
        return jdbi.withExtension(BookDao.class, dao -> dao.insertBook(this.Title, this.Created_at, this.getUpdated_at(), this.Slug, this.ISBN, this.Subtitle, this.Subjects, this.Cover_photo_url));
    }

    public void deleteBook(Jdbi jdbi, Integer id) {
        jdbi.useExtension(BookDao.class, dao -> dao.deleteBook(id));
    }

    public Book getBookById(Jdbi jdbi, int bookID) {
        return jdbi.withExtension(BookDao.class, Dao -> Dao.getBooksById(bookID));
    }

    private Date createDate(String dateString) {
        String pattern = "dd/MM/yy HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            return new Date();
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "BookID=" + BookId +
                ", Title='" + Title + '\'' +
                ", Authors='" + Authors + '\'' +
                ", Created_at='" + Created_at + '\'' +
                ", Updated_at='" + Updated_at + '\'' +
                ", Slug='" + Slug + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", Subtitle='" + Subtitle + '\'' +
                ", Subjects='" + Subjects + '\'' +
                ", Cover_photo_url='" + Cover_photo_url + '\'' +
                '}';
    }

    public long insertNewBook(Jdbi jdbi) {
        return jdbi.withExtension(BookDao.class, dao -> dao.insertBookRetrieveId(this.Title, this.Created_at, this.getUpdated_at(), this.Slug, this.ISBN, this.Subtitle, this.Subjects, this.Cover_photo_url));
    }
}


