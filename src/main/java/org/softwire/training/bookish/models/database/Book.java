package org.softwire.training.bookish.models.database;


public class Book {
    Integer BookID;
    String Title;
    String Authors;
    Integer Category;
    String Created_at;
    String Updated_at;
    String Slug;
    String ISBN;
    String Subtitle;
    String Subjects;
    String Cover_photo_url;

    public String getAuthors() {
        return Authors;
    }

    public Integer getBookID() {
        return BookID;
    }

    public String getTitle() {
        return Title;
    }

    public Integer getCategory() {
        return Category;
    }

    public String getCreated_at() {
        return Created_at;
    }

    public String getUpdated_at() {
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

    public void setCategory(Integer category) {
        Category = category;
    }

    public void setCreated_at(String created_at) {
        Created_at = created_at;
    }

    public void setUpdated_at(String updated_at) {
        Updated_at = updated_at;
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


    @Override
    public String toString() {
        return "Book{" +
                "BookID=" + BookID +
                ", Title='" + Title + '\'' +
                ", Authors='" + Authors + '\'' +
                ", Category=" + Category +
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


