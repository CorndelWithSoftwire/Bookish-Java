use bookish;

select * from author;

select * from user;

select * from book;

select * from copy_registry;

SELECT * FROM user u, copy_registry c WHERE u.id = c.borrowed_by;

DELETE FROM book WHERE id = 1;

DELETE FROM user where id = 2;





