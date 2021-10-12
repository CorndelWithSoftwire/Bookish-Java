package org.softwire.training.bookish.models.database;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.models.dao.LibrarianDao;

import java.util.List;

public class Librarian {
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void getLibrarianfromDatabase(Jdbi jdbi, String username) throws ArrayIndexOutOfBoundsException {
		List<Librarian> temp = jdbi.withExtension(LibrarianDao.class, dao -> dao.getLibrarian(username));
			this.username = temp.get(0).username;
	}

	public void insertLibrarianIntoDb(Jdbi jdbi) {
		Librarian librarian = jdbi.withExtension(LibrarianDao.class, dao -> {
			dao.createLibrarian(this.username);
					return null;
		});
	}

	@Override
	public String toString() {
		return "Librarian{" +
				"username='" + username + '\'' +
				'}';
	}
}
