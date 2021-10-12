package org.softwire.training.bookish.models.database;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Authors {
    private final Set<Author> authors = new HashSet<>();

    public Authors(Books arrayOfBooks) {
        AtomicInteger increment = new AtomicInteger();
        arrayOfBooks.booksList.forEach(book -> {
            String author = book.getAuthors();
            String[] multipleAuthors = author.split(",");
            for (String each : multipleAuthors) {
                String author_name = each;
                if (each.contains("\"")) {
                    author_name = each.replace("\"", "");
                }
                authors.add(new Author(increment.getAndIncrement(), author_name));
            }
        });
    }

    public Set<Author> getAuthors() {
        return authors;
    }


    @Override
    public String toString() {
        return "Authors{" +
                "authors=" + authors +
                '}';
    }
}
