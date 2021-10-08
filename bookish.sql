CREATE TABLE author (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    place_of_birth VARCHAR(255) NULL
);

CREATE TABLE user (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    forename VARCHAR(255) NOT NULL,
    surename VARCHAR(255) NOT NULL
);

CREATE TABLE book (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    ISBN INT(10),
    published_date VARCHAR(255) NULL,
    publisher VARCHAR(255) NULL,
    genre VARCHAR(255) NULL,
    summary VARCHAR(255) NULL,
    number_of_copies INT NOT NULL,
    author_id INT NOT NULL,
    FOREIGN KEY (author_id) REFERENCES author (id)
);

CREATE TABLE copy_registry (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    book_id INT NOT NULL,
    FOREIGN KEY (book_id) REFERENCES book (id),
    borrowed_by INT NOT NULL,
    FOREIGN KEY (borrowed_by) REFERENCES user (id),
    return_date VARCHAR(10)
);

-- DROP TABLE author;
-- DROP TABLE book;
-- DROP TABLE user;
-- DROP TABLE copy_registry;