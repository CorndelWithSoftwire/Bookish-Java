package org.softwire.training.bookish.models.database;

import org.jdbi.v3.core.result.LinkedHashMapRowReducer;
import org.jdbi.v3.core.result.RowView;

import java.util.Map;

public class BookAuthorReducer
        implements LinkedHashMapRowReducer<Integer, Author> {
    @Override
    public void accumulate(final Map<Integer, Author> map,
                           final RowView rowView) {

        final Author author = rowView.getRow(Author.class);

        final Author authorToBook
                = map.computeIfAbsent(author.getId(),
                id -> author);

        authorToBook.addAuthorsBook(rowView.getRow(Book.class));
    }
}