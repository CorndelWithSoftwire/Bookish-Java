package org.softwire.training.bookish.models.database;

import org.jdbi.v3.core.result.LinkedHashMapRowReducer;
import org.jdbi.v3.core.result.RowView;

import java.util.Map;

public class BookAndAuthorReducer
        implements LinkedHashMapRowReducer<Integer, Book> {
    @Override
    public void accumulate(final Map<Integer, Book> map,
                           final RowView rowView) {

        final Book book = rowView.getRow(Book.class);

        final Book bookToAuthor
                = map.computeIfAbsent(book.getId(),
                id -> book);

        bookToAuthor.setAuthorName(rowView.getRow(Author.class).getName());
    }
}
