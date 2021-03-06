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
TITLE VARCHAR(255),
AUTHOR_ID INT NOT NULL,
PRIMARY KEY(BOOK_ID),
FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHORS(AUTHOR_ID)
);

CREATE TABLE AUTHOR_BOOKS_RELATION(
AUTHOR_BOOKS_RELATION_ID INT NOT NULL AUTO_INCREMENT,
AUTHOR_ID INT NOT NULL,
BOOK_ID INT NOT NULL,
PRIMARY KEY(AUTHOR_BOOKS_RELATION_ID),
FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHORS(AUTHOR_ID),
FOREIGN KEY (BOOK_ID) REFERENCES BOOKS(BOOK_ID)
);

CREATE TABLE BOOK_CATEGORY_RELATION(
BOOK_CATEGORY_RELATION_ID INT NOT NULL AUTO_INCREMENT,
BOOK_CATEGORY_ID INT NOT NULL,
BOOK_ID INT NOT NULL,
PRIMARY KEY(BOOK_CATEGORY_RELATION_ID),
FOREIGN KEY (BOOK_CATEGORY_ID) REFERENCES BOOK_CATEGORIES(BOOK_CATEGORY_ID),
FOREIGN KEY (BOOK_ID) REFERENCES BOOKS(BOOK_ID)
);
