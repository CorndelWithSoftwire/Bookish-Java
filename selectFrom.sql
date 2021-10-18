use bookish;

select * from book;

select * from user;

select title, author_id from book where title = 'BECOMING';

select * from copy_registry;

SELECT * FROM author WHERE id = 1;

SELECT * FROM user u, copy_registry c WHERE u.id = c.borrowed_by;

DELETE FROM book WHERE id = 1;

DELETE FROM user where id = 2;


SELECT author.name AS author_name, book.title AS book_title
FROM book JOIN author ON book.author_id = author.id where book.title LIKE '%the%';

SELECT book.id, book.title, book.ISBN, book.published_date, book.publisher,
       book.genre, book.number_of_copies, book.author_id,
       author.name AS aname, author.id AS aid
FROM book JOIN author ON book.author_id = author.id;

SELECT book.id, book.title, book.ISBN, book.published_date, book.publisher, book.genre, book.number_of_copies, book.author_id, author.name AS aname, author.id AS aid FROM book JOIN author ON book.author_id = author.id where book.title LIKE '%the%';

INSERT INTO book (id, title, ISBN, published_date, publisher, genre, number_of_copies, author_id)
VALUES (4, 'Harry Potter and the Goblet of Fire', '1111111151', '1997', 'Scholastic', 'fantasy', 2, 1)
ON DUPLICATE KEY UPDATE id=VALUES(id),
                        title=VALUES(title),
                        ISBN=VALUES(ISBN),
                        published_date=VALUES(published_date),
                        publisher=VALUES(publisher),
                        genre=VALUES(genre),
                        number_of_copies=VALUES(number_of_copies),
                        author_id=VALUES(author_id);

SELECT author.name AS aname, author.id AS aid, author.place_of_birth as aplace_of_birth,
       book.id, book.title, book.ISBN, book.published_date, book.publisher, book.genre, book.number_of_copies, book.author_id

FROM book JOIN author ON book.author_id = author.id where book.title LIKE '%suzanne%' OR author.name LIKE '%suzanne%'