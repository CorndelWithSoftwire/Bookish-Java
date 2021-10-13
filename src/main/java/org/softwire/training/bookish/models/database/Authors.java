package org.softwire.training.bookish.models.database;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Authors {
    private final Set<Author> authors = new HashSet<>();

    public Authors(Books arrayOfBooks) {
        AtomicInteger increment = new AtomicInteger();
        arrayOfBooks.booksList.forEach(book -> {
            String author = book.getAuthors();
            String[] multipleAuthors = author.split(",");
            for (String each : multipleAuthors) {
                String author_name = each.trim();
                if (each.contains("\"")) {
                    author_name = recursiveTrim(each.replaceAll("\"", ""));
                }
                authors.add(new Author(increment.getAndIncrement(), author_name));
            }
        });
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    private static String recursiveTrim(String string) {
        while (string.charAt(0) == ' ' || string.charAt(string.length()-1) == ' ') {
            string = string.trim();
        }
        return string;
    }

    @Override
    public String toString() {
        return "Authors{" +
                "authors=" + authors +
                '}';
    }
}
