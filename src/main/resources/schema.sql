CREATE TABLE Transactions (
                              TransactionId int PRIMARY KEY,
                              Amount int
);
CREATE TABLE Customers (
                           CustomerId int PRIMARY KEY,
                           LastName varchar(255),
                           FirstName varchar(255)
);
CREATE TABLE Accounts (
                          AccountId int PRIMARY KEY,
                          CustomerId int,
                          TransactionId int,
                          FOREIGN KEY (CustomerId) REFERENCES Customers(CustomerId),
                          FOREIGN KEY (TransactionId) REFERENCES Transactions(TransactionId)

);
