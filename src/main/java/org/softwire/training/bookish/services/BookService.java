package org.softwire.training.bookish.services;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService extends DatabaseService {

    public static List<Book> getAllBooks() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM Books")
                        .map((rs, ctx) -> new Book(rs.getInt("ID"), rs.getString("Title"),
                                rs.getString("Author"), rs.getString("ISBN"),
                                rs.getString("Genre"), rs.getInt("Copies"))).list());
    }

    public void addBook(Book inputBook) {
        System.out.println(inputBook.getISBN());
        List<Book> books = getAllBooks();
        for (Book book : books){
            if (book.getISBN().equals(inputBook.getISBN())){
                book.setCopies(book.getCopies()+inputBook.getCopies());
                return;
            }
        }
        jdbi.useHandle(handle ->
                handle.createUpdate("INSERT INTO Books (Title, Author, ISBN, Genre, Copies)" +
                                " VALUES (:title, :author, :ISBN, :genre, :copies)")
                        .bind("title", inputBook.getTitle())
                        .bind("author", inputBook.getAuthor())
                        .bind("ISBN", inputBook.getISBN())
                        .bind("genre", inputBook.getGenre())
                        .bind("copies", inputBook.getCopies())
                        .execute()
        );
    }

    public void deleteBook(int bookID) {
        jdbi.useHandle(handle ->
                handle.createUpdate("DELETE FROM Users WHERE id = :id")
                        .bind("id", bookID)
                        .execute()
        );
    }
}
