package org.softwire.training.bookish.models.database;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.models.dao.BookAuthorDao;

public class BookAuthor {
	private int Book;
	private int Author;

	public BookAuthor() {}

	public int getBook() {
		return Book;
	}

	public void setBook(int book) {
		Book = book;
	}

	public int getAuthor() {
		return Author;
	}

	public void setAuthor(int author) {
		Author = author;
	}

	public void insertBookAuthor(Jdbi jdbi) {
		jdbi.useExtension(BookAuthorDao.class, dao -> dao.insertBookAuthor(this.Book, this.Author));
	}
}
