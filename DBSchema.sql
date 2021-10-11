-- Exported from QuickDBD: https://www.quickdatabasediagrams.com/
-- NOTE! If you have used non-SQL datatypes in your design, you will have to change these here.

-- Modify this code to update the DB schema diagram.
-- To reset the sample schema, replace everything with
-- two dots ('..' - without quotes).

CREATE TABLE `Book` (
    `BookID` int  NOT NULL ,
    `Title` varchar(255)  NOT NULL ,
    `Authors` int  NOT NULL ,
    `Category` int  NOT NULL ,
    `Created_at` date  NOT NULL ,
    `Updated_at` date  NOT NULL ,
    `Slug` varchar(255)  NOT NULL ,
    `ISBN` varchar(255)  NULL ,
    `Subtitle` varchar(255)  NULL ,
    `Subjects` varchar(255)  NULL ,
    `Cover_photo_url` varchar(255)  NULL ,
    PRIMARY KEY (
        `BookID`
    )
);

CREATE TABLE `Copies` (
    `BookID` int  NOT NULL ,
    PRIMARY KEY (
        `BookID`
    )
);

CREATE TABLE `BookAuthor` (
    `Author_pointer` int  NOT NULL ,
    PRIMARY KEY (
        `Author_pointer`
    )
);

CREATE TABLE `Authors` (
    `AuthorID` int  NOT NULL ,
    `First_name` varchar(32)  NOT NULL ,
    `Last_name` varchar(32)  NOT NULL ,
    PRIMARY KEY (
        `AuthorID`
    )
);

CREATE TABLE `Categories` (
    `CategoryID` int  NOT NULL ,
    `Category` varchar(32)  NULL ,
    PRIMARY KEY (
        `CategoryID`
    )
);

CREATE TABLE `Users` (
    `Username` varchar(32)  NOT NULL ,
    `Password_hash` varchar(32)  NOT NULL ,
    `Email` varchar(255)  NOT NULL ,
    `Phone_number` long  NOT NULL ,
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
    `BorrowID` int  NOT NULL ,
    `Borrowed_copy_ID` int  NOT NULL ,
    `Username` varchar(32)  NOT NULL ,
    `Check_out_date` date  NOT NULL ,
    `Check_in_date` date  NOT NULL ,
    `Due_date` date  NOT NULL ,
    PRIMARY KEY (
        `BorrowID`
    )
);

ALTER TABLE `Book` ADD CONSTRAINT `fk_Book_Authors` FOREIGN KEY(`Authors`)
REFERENCES `BookAuthor` (`Author_pointer`);

ALTER TABLE `Book` ADD CONSTRAINT `fk_Book_Category` FOREIGN KEY(`Category`)
REFERENCES `Categories` (`CategoryID`);

ALTER TABLE `Copies` ADD CONSTRAINT `fk_Copies_BookID` FOREIGN KEY(`BookID`)
REFERENCES `Book` (`BookID`);

ALTER TABLE `Authors` ADD CONSTRAINT `fk_Authors_AuthorID` FOREIGN KEY(`AuthorID`)
REFERENCES `BookAuthor` (`Author_pointer`);

ALTER TABLE `Users` ADD CONSTRAINT `fk_Users_Username` FOREIGN KEY(`Username`)
REFERENCES `Librarians` (`Username`);

ALTER TABLE `Borrows` ADD CONSTRAINT `fk_Borrows_Borrowed_copy_ID` FOREIGN KEY(`Borrowed_copy_ID`)
REFERENCES `Copies` (`BookID`);

ALTER TABLE `Borrows` ADD CONSTRAINT `fk_Borrows_Username` FOREIGN KEY(`Username`)
REFERENCES `Users` (`Username`);

CREATE INDEX `idx_Book_Title`
ON `Book` (`Title`);

CREATE INDEX `idx_Authors_First_name`
ON `Authors` (`First_name`);

CREATE INDEX `idx_Authors_Last_name`
ON `Authors` (`Last_name`);

