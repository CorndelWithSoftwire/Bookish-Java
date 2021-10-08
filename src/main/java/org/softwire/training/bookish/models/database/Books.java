package org.softwire.training.bookish.models.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Books {
    public ArrayList<Book> booksList;

    public Books(String filePath) {
        this.booksList = readCSV(filePath);
    }

    ArrayList<Book> readCSV(String filePath) {
        List<List<String>> bookRecords = new ArrayList<>();
        ArrayList<Book> book_array = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
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
            book.setCategory(Integer.valueOf(e.get(3)));
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


}


