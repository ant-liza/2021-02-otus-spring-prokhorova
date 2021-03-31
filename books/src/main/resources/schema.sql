CREATE TABLE AUTHORS(
AUTHOR_ID INT NOT NULL AUTO_INCREMENT,
FIRSTNAME VARCHAR(255),
LASTNAME VARCHAR(255),
NICKNAME VARCHAR(255),
PRIMARY KEY(AUTHOR_ID)
);

CREATE TABLE BOOK_CATEGORIES(
BOOK_CATEGORY_ID INT NOT NULL AUTO_INCREMENT,
BOOK_CATEGORY_NAME VARCHAR(255),
PRIMARY KEY(BOOK_CATEGORY_ID)
);

CREATE TABLE BOOKS(
BOOK_ID INT NOT NULL AUTO_INCREMENT,
AUTHOR_ID INT NOT NULL,
BOOK_CATEGORY_ID INT NOT NULL,
DESCRIPTION VARCHAR(255),
PRIMARY KEY(BOOK_ID),
FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHORS(AUTHOR_ID),
FOREIGN KEY (BOOK_CATEGORY_ID) REFERENCES BOOK_CATEGORIES(BOOK_CATEGORY_ID)
);