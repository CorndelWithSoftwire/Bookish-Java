package org.softwire.training.bookish.models.database;


import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.models.dao.BookDao;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Book {
    Integer BookID;
    String Title;
    String Authors;
    Date Created_at;
    Date Updated_at;
    String Slug;
    String ISBN;
    String Subtitle;
    String Subjects;
    String Cover_photo_url;

    public String getAuthors() {
        return Authors;
    }

    public Book(){}

    public Integer getBookID() {
        return BookID;
    }

    public String getTitle() {
        return Title;
    }

    public Date getCreated_at() {
        return Created_at;
    }

    public Date getUpdated_at() {
        return Updated_at;
    }

    public String getSlug() {
        return Slug;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getSubtitle() {
        return Subtitle;
    }

    public String getSubjects() {
        return Subjects;
    }

    public String getCover_photo_url() {
        return Cover_photo_url;
    }

    public void setBookID(Integer bookID) {
        BookID = bookID;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setAuthors(String authors) {
        Authors = authors;
    }

    public void setCreated_at(String created_at) {
        Created_at = createDate(created_at);
    }

    public void setUpdated_at(String updated_at) {
        Updated_at = createDate(updated_at);
    }

    public void setSlug(String slug) {
        Slug = slug;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setSubtitle(String subtitle) {
        Subtitle = subtitle;
    }

    public void setSubjects(String subjects) {
        Subjects = subjects;
    }

    public void setCover_photo_url(String cover_photo_url) {
        Cover_photo_url = cover_photo_url;
    }

    public void insertBook(Jdbi jdbi) {
       jdbi.useExtension(BookDao.class, dao -> dao.insertBook(this.BookID, this.Title, this.Created_at, this.getUpdated_at(), this.Slug, this.ISBN, this.Subtitle, this.Subjects, this.Cover_photo_url));
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
                "BookID=" + BookID +
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


}


