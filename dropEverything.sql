ALTER TABLE copy_registry
    DROP FOREIGN KEY copy_registry_ibfk_1;
    
ALTER TABLE copy_registry
    DROP FOREIGN KEY copy_registry_ibfk_2;

DROP TABLE copy_registry;

DROP TABLE book;

DROP TABLE author;

DROP TABLE user;




