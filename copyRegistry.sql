select * from copy_registry;
select * from user;
select * from book;

UPDATE user
SET forename='Bec'
WHERE id=6;

INSERT INTO book (id, title, ISBN, published_date, publisher, genre, number_of_copies, author_id)
VALUES (4, 'Harry Potter and the Goblet of Your Mum', '1111111151', '1997', 'Scholastic', 'fantasy', 2, 1)
ON DUPLICATE KEY UPDATE id=VALUES(id),
title=VALUES(title),
ISBN=VALUES(ISBN),
published_date=VALUES(published_date),
publisher=VALUES(publisher),
genre=VALUES(genre),
number_of_copies=VALUES(number_of_copies),
author_id=VALUES(author_id);

-- HELLO EVERYONE