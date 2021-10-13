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

	public void insertCopyIntoDb(Jdbi jdbi) {
		jdbi.useExtension(CopyDao.class, dao -> dao.insertCopy(this.copyId, this.bookId));
	}
}
