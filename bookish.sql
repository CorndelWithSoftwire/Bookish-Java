USE bookish;

-- STEP TWO!!!!

CREATE TABLE author (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    place_of_birth VARCHAR(255) NULL
);

INSERT INTO author
(name, place_of_birth)
VALUES
('J.K. Rowling', 'Yate'),
('Jacqueline Wilson', 'Bath'),
('Michelle Obama', 'United States'),
('James Clear' , 'United States'),
('William Shakespeare', 'United States'),
('Charles Dickens', 'United States'),
('Suzanne Collins', 'United States'),
('Stephenie Meyer', 'United States'),
('Napoleon Hill', 'United States'),
('Vex King', 'United States'),
('Malorie Blackman', 'London');

CREATE TABLE user (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    forename VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL
);

INSERT INTO user
(forename, surname)
VALUES
('Bori', 'Tobias'),
('Amber', 'Shand'),
('Tom', 'Brough'),
('Harvey', 'Parker-Acheson'),
('Van', 'Thoai Nguyen'),
('Bec', 'Sun');



CREATE TABLE book (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    ISBN INT(10),
    published_date VARCHAR(255) NULL,
    publisher VARCHAR(255) NULL,
    genre VARCHAR(255) NULL,
    number_of_copies INT NOT NULL,
    author_id INT NOT NULL,
    FOREIGN KEY (author_id) REFERENCES author (id)
);

INSERT INTO book (title, ISBN, author_id, published_date, publisher, genre, number_of_copies) VALUES
('Harry Potter and the Sorcerer\'s Stone', 1111111121, 1, '1997', 'Scholastic', 'fantasy', 2),
('Harry Potter and the Chamber of Secrets', 1111111131, 1, '1998', 'Arthur A. Levine Books', 'fantasy', 1),
('Harry Potter and the Prisoner of Azkaban', 1111111141, 1, '1999', 'Scholastic', 'fantasy', 1),
('Harry Potter and the Goblet of Fire', 1111111151, 1, '2002', 'Scholastic', 'fantasy', 2),
('Harry Potter and the Order of the Phoenix', 1111111161, 1, '2003', 'Scholastic', 'fantasy', 1),
('Harry Potter and the Half-Blood Prince', 1111111171, 1, '2005', 'Scholastic', 'fantasy', 1),
('Harry Potter and the Deathly Hallows', 1111111181, 1, '2007', 'Arthur A. Levine Books', 'fantasy', 1),
('Double Act', 1111111114, 2, 2006, 'Yearling', 'childrens', 1),
('Becoming', 1111111115, 3, 2018, 'Crown', 'nonfiction', 1),
('Atomic Habits: An Easy & Proven Way to Build Good Habits & Break Bad Ones', 1111111116, 4, 2018, 'Avery', 'self help', 1),
('Romeo and Juliet', 1111111117, 5, 1597, 'Washington Square Press', 'classic', 1),
('Hamlet', 1111111118, 5, 1601, 'Cambridge University Press', 'classic', 1),
('A Midsummer Night\'s Dream', 1111111119, 5, 1595, 'Simon & Schuster Paperbacks', 'classic', 1),
('A Christmas Carol', 1111111120, 2, 1843, 'Bethany House Publishers', 'classic', 1),
('The Hunger Games', 1111111111, 7, 2008, 'Scholastic', 'young adult', 1),
('Catching Fire', 1111111112, 7, 2009, 'Scholastic', 'young adult', 1),
('Mockingjay', 1111111113, 7, 2010, 'Scholastic', 'young adult', 1),
('Twilight', 1111111122, 8, 2005, 'Little, Brown and Company', 'young adult', 1),
('New Moon', 1111111124, 8, 2006, 'Little, Brown and Company', 'young adult', 1),
('Eclipse', 1111111123, 8, 2007, 'Little, Brown and Company', 'young adult', 1),
('Breaking Dawn', 1111111125, 8, 2008, 'Little, Brown and Company', 'young adult', 1);

CREATE TABLE copy_registry (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    book_id INT NOT NULL,
    FOREIGN KEY (book_id) REFERENCES book (id),
    borrowed_by INT,
    FOREIGN KEY (borrowed_by) REFERENCES user (id),
    return_date VARCHAR(10)
);

INSERT INTO copy_registry (book_id, borrowed_by, return_date) VALUES
(1,NULL,NULL),
(1,NULL,NULL),
(2,NULL,NULL),
(3, NULL,NULL),
(4, NULL,NULL),
(4, NULL,NULL),
(5, NULL,NULL),
(6, NULL,NULL),
(7, NULL,NULL),
(8, NULL,NULL),
(9, NULL,NULL),
(10, NULL,NULL),
(11, 5, '2021-12-31'),
(12, 6, '2021-12-31'),
(13, 6, '2021-12-31'),
(14, 3, '2021-12-31'),
(15, 2, '2021-12-31'),
(16, 2, '2021-12-31'),
(17, NULL,NULL),
(18, NULL,NULL),
(19, NULL,NULL),
(20, 5, '2021-12-31'),
(21, NULL,NULL);