use bookish;

select * from book;

select * from user;

select title, author_id from book where title = 'BECOMING';

select * from copy_registry;

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
