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
    book_cover_url VARCHAR(255) NULL,
    FOREIGN KEY (author_id)
        REFERENCES author (id)
        ON DELETE CASCADE
);

INSERT INTO book (title, ISBN, author_id, published_date, publisher, genre, number_of_copies, book_cover_url) VALUES
('Harry Potter and the Sorcerer\'s Stone', 1111111121, 1, '1997', 'Scholastic', 'fantasy', 2, 'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9781/3385/9781338596700.jpg'),
('Harry Potter and the Chamber of Secrets', 1111111131, 1, '1998', 'Arthur A. Levine Books', 'fantasy', 1, 'https://images-na.ssl-images-amazon.com/images/I/51kdLYNJFsL.jpg'),
('Harry Potter and the Prisoner of Azkaban', 1111111141, 1, '1999', 'Scholastic', 'fantasy', 1, 'https://m.media-amazon.com/images/I/51Dfqo6jR5L.jpg'),
('Harry Potter and the Goblet of Fire', 1111111151, 1, '2002', 'Scholastic', 'fantasy', 2, 'https://images-eu.ssl-images-amazon.com/images/I/41AF6KHRGML._SY291_BO1,204,203,200_QL40_ML2_.jpg'),
('Harry Potter and the Order of the Phoenix', 1111111161, 1, '2003', 'Scholastic', 'fantasy', 1, 'https://images-na.ssl-images-amazon.com/images/I/5123M2VGGKL.jpg'),
('Harry Potter and the Half-Blood Prince', 1111111171, 1, '2005', 'Scholastic', 'fantasy', 1, 'https://images-na.ssl-images-amazon.com/images/I/51ALVaVFspL._SX322_BO1,204,203,200_.jpg'),
('Harry Potter and the Deathly Hallows', 1111111181, 1, '2007', 'Arthur A. Levine Books', 'fantasy', 1, 'https://images-na.ssl-images-amazon.com/images/I/51tB0kftR-L.jpg'),
('Double Act', 1111111114, 2, 2006, 'Yearling', 'childrens', 1, 'https://images-na.ssl-images-amazon.com/images/I/81daTB1TMML.jpg'),
('Becoming', 1111111115, 3, 2018, 'Crown', 'nonfiction', 1, 'https://images-na.ssl-images-amazon.com/images/I/71L5yvsjUQL.jpg'),
('Atomic Habits: An Easy & Proven Way to Build Good Habits & Break Bad Ones', 1111111116, 4, 2018, 'Avery', 'self help', 1, 'https://images-na.ssl-images-amazon.com/images/I/81iAADNy2NL.jpg'),
('Romeo and Juliet', 1111111117, 5, 1597, 'Washington Square Press', 'classic', 1, 'http://prodimage.images-bn.com/pimages/9780198321668_p0_v1_s1200x630.jpg'),
('Hamlet', 1111111118, 5, 1601, 'Cambridge University Press', 'classic', 1, 'https://www.newtimes.co.rw/sites/default/files/main/articles/2010/12/04/-shakes.jpg'),
('A Midsummer Night\'s Dream', 1111111119, 5, 1595, 'Simon & Schuster Paperbacks', 'classic', 1, 'https://images-na.ssl-images-amazon.com/images/I/61-R264ie2L.jpg'),
('A Christmas Carol', 1111111120, 2, 1843, 'Bethany House Publishers', 'classic', 1, 'https://kbimages1-a.akamaihd.net/3d0eb956-4ff4-41c2-afa0-5e8504d2b8fb/353/569/90/False/a-christmas-carol-184.jpg'),
('The Hunger Games', 1111111111, 7, 2008, 'Scholastic', 'young adult', 1, 'https://images-na.ssl-images-amazon.com/images/I/71WSzS6zvCL.jpg'),
('Catching Fire', 1111111112, 7, 2009, 'Scholastic', 'young adult', 1, 'https://images-na.ssl-images-amazon.com/images/I/61VUik8NJ8L.jpg'),
('Mockingjay', 1111111113, 7, 2010, 'Scholastic', 'young adult', 1, 'https://images-na.ssl-images-amazon.com/images/I/61n5pTMvmpL.jpg'),
('Twilight', 1111111122, 8, 2005, 'Little, Brown and Company', 'young adult', 1, 'https://images-na.ssl-images-amazon.com/images/I/318nujF5v5L._SX327_BO1,204,203,200_.jpg'),
('New Moon', 1111111124, 8, 2006, 'Little, Brown and Company', 'young adult', 1, 'https://images-na.ssl-images-amazon.com/images/I/41lszRw+RWL._SX316_BO1,204,203,200_.jpg'),
('Eclipse', 1111111123, 8, 2007, 'Little, Brown and Company', 'young adult', 1, 'https://images-na.ssl-images-amazon.com/images/I/415z605z95L.jpg'),
('Breaking Dawn', 1111111125, 8, 2008, 'Little, Brown and Company', 'young adult', 1, 'https://images-na.ssl-images-amazon.com/images/I/51sjfP080VL.jpg');

CREATE TABLE copy_registry (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    book_id INT NOT NULL,
    borrowed_by INT,
    return_date VARCHAR(10),
    FOREIGN KEY (book_id)
        REFERENCES book (id)
        ON DELETE CASCADE,
    FOREIGN KEY (borrowed_by)
        REFERENCES user (id)
        ON DELETE SET NULL

);

INSERT INTO copy_registry (book_id, borrowed_by, return_date) VALUES
(11, 5, '2021-12-31'),
(12, 6, '2021-12-31'),
(13, 6, '2021-12-31'),
(14, 3, '2021-12-31'),
(15, 2, '2021-12-31'),
(16, 2, '2021-12-31'),
(20, 5, '2021-12-31');

