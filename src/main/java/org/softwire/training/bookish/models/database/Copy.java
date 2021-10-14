package org.softwire.training.bookish.models.database;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.models.dao.CopyDao;

public class Copy {
	private int copyId;
	private int bookId;

	public Copy() {}

	public int getCopyId() {
		return copyId;
	}

	public void setCopyId(int copyId) {
		this.copyId = copyId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int insertCopyIntoDb(Jdbi jdbi) {
		return jdbi.withExtension(CopyDao.class, dao -> dao.insertCopy(this.bookId));
	}
}
