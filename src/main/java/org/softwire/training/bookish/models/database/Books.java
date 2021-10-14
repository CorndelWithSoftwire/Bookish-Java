package org.softwire.training.bookish.models.database;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.models.dao.BookDao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Books {
    public List<Book> booksList;

    public Books(String filePath) {
        this.booksList = readCSV(filePath);
    }

    public Books() {
    }

    ArrayList<Book> readCSV(String filePath) {
        List<List<String>> bookRecords = new ArrayList<>();
        ArrayList<Book> book_array = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                String defaultValue = Arrays.asList(values).get(2).isEmpty() ? "no author assigned" : Arrays.asList(values).get(2);
                Arrays.asList(values).set(2, defaultValue);
                for (int i = 0; i < Arrays.asList(values).size(); i++) {
                    if (Arrays.asList(values).get(i).isEmpty()) {
                        Arrays.asList(values).set(i, null);
                    }
                }
                bookRecords.add(Arrays.asList(values));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        bookRecords.forEach(e -> {
            Book book = new Book();
            book.setBookID(Integer.valueOf(e.get(0)));
            book.setTitle(e.get(1));
            book.setAuthors(e.get(2));
            book.setCreated_at(e.get(4));
            book.setUpdated_at(e.get(5));
            book.setSlug(e.get(6));
            book.setISBN(e.get(7));
            book.setSubtitle(e.get(8));
            book.setSubjects(e.get(9));
            book.setCover_photo_url(e.get(10));
            book_array.add(book);
        });
        return book_array;
    }


    public List<BookDict> getBooksList(Jdbi jdbi, int page) {
        return jdbi.withExtension(BookDao.class, Dao -> Dao.getAllBooks(50, page * 50));
    }




}


