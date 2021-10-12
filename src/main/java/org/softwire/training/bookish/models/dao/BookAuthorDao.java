package org.softwire.training.bookish.models.dao;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface BookAuthorDao {
	@SqlUpdate("INSERT INTO BookAuthor VALUES (:book, :author)")
	void insertBookAuthor(@Bind("book") int BookId, @Bind("author") int AuthorId);
}
