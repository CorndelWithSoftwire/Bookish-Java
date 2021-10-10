-- Exported from QuickDBD: https://www.quickdatabasediagrams.com/
-- NOTE! If you have used non-SQL datatypes in your design, you will have to change these here.


CREATE TABLE `Book` (
    `BookId` int  NOT NULL ,
    `Title` varchar(255)  NOT NULL ,
    `Category` int  NOT NULL ,
    `CreatedAt` date  NOT NULL ,
    `UpdatedAt` date  NOT NULL ,
    `Slug` varchar(255)  NOT NULL ,
    `Isbn` varchar(255)  NULL ,
    `Subtitle` varchar(255)  NULL ,
    `Subjects` varchar(255)  NULL ,
    `CoverPhotoUrl` varchar(255)  NULL ,
    PRIMARY KEY (
        `BookId`
    )
);

CREATE TABLE `Copies` (
    `CopyId` int  NOT NULL ,
    `BookId` int  NOT NULL ,
    PRIMARY KEY (
        `CopyId`
    )
);

CREATE TABLE `BookAuthor` (
    `Book` int  NOT NULL ,
    `Author` int  NOT NULL 
);

CREATE TABLE `Authors` (
    `AuthorId` int  NOT NULL ,
    `FirstName` varchar(32)  NOT NULL ,
    `LastName` varchar(32)  NOT NULL ,
    PRIMARY KEY (
        `AuthorId`
    )
);

CREATE TABLE `Categories` (
    `CategoryId` int  NOT NULL ,
    `Category` varchar(32)  NULL ,
    PRIMARY KEY (
        `CategoryId`
    )
);

CREATE TABLE `Users` (
    `Username` varchar(32)  NOT NULL ,
    `PasswordHash` varchar(32)  NOT NULL ,
    `Email` varchar(255)  NOT NULL ,
    `PhoneNumber` long  NOT NULL ,
    PRIMARY KEY (
        `Username`
    )
);

CREATE TABLE `Librarians` (
    `Username` varchar(32)  NOT NULL ,
    PRIMARY KEY (
        `Username`
    )
);

CREATE TABLE `Borrows` (
    `BorrowId` int  NOT NULL ,
    `BorrowedCopyId` int  NOT NULL ,
    `Username` varchar(32)  NOT NULL ,
    `CheckOutDate` date  NOT NULL ,
    `CheckInDate` date  NOT NULL ,
    `DueDate` date  NOT NULL ,
    PRIMARY KEY (
        `BorrowId`
    )
);

ALTER TABLE `Book` ADD CONSTRAINT `fk_Book_BookId` FOREIGN KEY(`BookId`)
REFERENCES `BookAuthor` (`Book`);

ALTER TABLE `Book` ADD CONSTRAINT `fk_Book_Category` FOREIGN KEY(`Category`)
REFERENCES `Categories` (`CategoryId`);

ALTER TABLE `Copies` ADD CONSTRAINT `fk_Copies_CopyId` FOREIGN KEY(`CopyId`)
REFERENCES `Borrows` (`BorrowedCopyId`);

ALTER TABLE `Copies` ADD CONSTRAINT `fk_Copies_BookId` FOREIGN KEY(`BookId`)
REFERENCES `Book` (`BookId`);

ALTER TABLE `Authors` ADD CONSTRAINT `fk_Authors_AuthorId` FOREIGN KEY(`AuthorId`)
REFERENCES `BookAuthor` (`Author`);

ALTER TABLE `Librarians` ADD CONSTRAINT `fk_Librarians_Username` FOREIGN KEY(`Username`)
REFERENCES `Users` (`Username`);

ALTER TABLE `Borrows` ADD CONSTRAINT `fk_Borrows_Username` FOREIGN KEY(`Username`)
REFERENCES `Users` (`Username`);

CREATE INDEX `idx_Book_Title`
ON `Book` (`Title`);

CREATE INDEX `idx_Authors_FirstName`
ON `Authors` (`FirstName`);

CREATE INDEX `idx_Authors_LastName`
ON `Authors` (`LastName`);

