DROP SCHEMA bookish;

CREATE SCHEMA bookish;

use bookish;

CREATE TABLE Book (BookId int NOT NULL, Title varchar(255)  NOT NULL, Category int  NOT NULL, CreatedAt varchar(255)  NOT NULL, UpdatedAt varchar(255)  NOT NULL, Slug varchar(255)  NOT NULL, Isbn varchar(255)  NULL, Subtitle varchar(255)  NULL, Subjects varchar(600)  NULL, CoverPhotoUrl varchar(255) NULL, PRIMARY KEY (BookId));

CREATE TABLE Copies (CopyId int  NOT NULL, BookId int  NOT NULL , PRIMARY KEY (CopyId));

CREATE TABLE BookAuthor (Book int  NOT NULL ,Author int  NOT NULL );

CREATE TABLE Authors (AuthorId int  NOT NULL, AuthorName varchar(255)  NOT NULL, PRIMARY KEY (AuthorId));

CREATE TABLE Users (Username varchar(32)  NOT NULL ,PasswordHash varchar(255)  NOT NULL ,Email varchar(255)  NOT NULL ,PhoneNumber long  NOT NULL ,PRIMARY KEY (Username));

CREATE TABLE Librarians (Username varchar(32)  NOT NULL ,PRIMARY KEY (Username));

CREATE TABLE Borrows (BorrowId int  NOT NULL ,BorrowedCopyId int  NOT NULL ,Username varchar(32)  NOT NULL ,CheckOutDate date  NOT NULL ,CheckInDate date  NOT NULL ,DueDate date  NOT NULL ,PRIMARY KEY (BorrowId) );

ALTER TABLE BookAuthor ADD CONSTRAINT fk_Book_BookId FOREIGN KEY(Book) REFERENCES Book (BookId);

ALTER TABLE Borrows ADD CONSTRAINT fk_Copies_CopyId FOREIGN KEY(BorrowedCopyId) REFERENCES Copies (CopyId);

ALTER TABLE Copies ADD CONSTRAINT fk_Copies_BookId FOREIGN KEY(BookId) REFERENCES Book (BookId);

ALTER TABLE BookAuthor ADD CONSTRAINT fk_Authors_AuthorId FOREIGN KEY(Author) REFERENCES Authors (AuthorId);

ALTER TABLE Librarians ADD CONSTRAINT fk_Librarians_Username FOREIGN KEY(Username) REFERENCES Users (Username);

ALTER TABLE Borrows ADD CONSTRAINT fk_Borrows_Username FOREIGN KEY(Username) REFERENCES Users (Username);

CREATE INDEX idx_Book_Title ON Book (Title);

CREATE INDEX idx_Authors_Name ON Authors (AuthorName);

ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'c7f/SGXS<80D1H/Iqf0PQp90@dicw(J?';
