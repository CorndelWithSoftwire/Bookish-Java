package org.softwire.training.bookish.models.database;

public class Librarian {
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Librarian{" +
				"username='" + username + '\'' +
				'}';
	}
}
