use bookish;

DROP TABLE IF EXISTS Books, Authors, Users, Loans, BooksToAuthors, Copies;

Create table Books(
ID INT AUTO_INCREMENT PRIMARY KEY,
Name varchar (255) NOT NULL,
ISBN varchar (13) NOT NULL,
PublishDate date NOT NULL
);

Create table Authors(
ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
Name varchar (255) NOT NULL

);
Create table Users(
ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
Name varchar (255),
Address varchar (255)
);

Create table BooksToAuthors(
BookID INT NOT NULL AUTO_INCREMENT,
AuthorID INT NOT NULL,
Foreign Key (BookID) References Books (ID),
Foreign Key (AuthorID) References Authors (ID)
);

Create table Copies(
ID int AUTO_INCREMENT PRIMARY KEY,
BookID int,
Foreign Key (BookID) References Books (ID)
);

Create table Loans(
ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
UserID INT NOT NULL,
Foreign Key (UserID) References Users (ID),
CopyID INT,
Foreign Key (CopyID) References Copies (ID),
ReturnedDate date
);