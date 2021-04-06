insert into authors (FIRSTNAME,LASTNAME,NICKNAME) values ('Agatha','Christie',null);
insert into authors (FIRSTNAME,LASTNAME,NICKNAME) values ('Stephen','King',null);
insert into authors (FIRSTNAME,LASTNAME,NICKNAME) values ('George','Martin',null);

insert into book_categories (BOOK_CATEGORY_NAME) values ('Фантастика');
insert into book_categories (BOOK_CATEGORY_NAME) values ('Детектив');

insert into books (AUTHOR_ID,TITLE) values (1,'Десять негритят');
insert into books (AUTHOR_ID,TITLE) values (1,'Вилла Белый Конь');
insert into books (AUTHOR_ID,TITLE) values (2,'Стрелок');
insert into books (AUTHOR_ID,TITLE) values (2,'Извлечение троих');
insert into books (AUTHOR_ID,TITLE) values (2,'Мистер Мерседес');
insert into books (AUTHOR_ID,TITLE) values (2,'Кто нашел, берет себе');
insert into books (AUTHOR_ID,TITLE) values (2,'Пост сдал');
insert into books (AUTHOR_ID,TITLE) values (3,'Игра престолов');

insert into AUTHOR_BOOKS_RELATION (AUTHOR_ID,BOOK_ID) values (1,1);
insert into AUTHOR_BOOKS_RELATION (AUTHOR_ID,BOOK_ID) values (1,2);
insert into AUTHOR_BOOKS_RELATION (AUTHOR_ID,BOOK_ID) values (2,3);
insert into AUTHOR_BOOKS_RELATION (AUTHOR_ID,BOOK_ID) values (2,4);
insert into AUTHOR_BOOKS_RELATION (AUTHOR_ID,BOOK_ID) values (2,5);
insert into AUTHOR_BOOKS_RELATION (AUTHOR_ID,BOOK_ID) values (2,6);
insert into AUTHOR_BOOKS_RELATION (AUTHOR_ID,BOOK_ID) values (2,7);

insert into BOOK_CATEGORY_RELATION (BOOK_ID,BOOK_CATEGORY_ID) values (1,2);
insert into BOOK_CATEGORY_RELATION (BOOK_ID,BOOK_CATEGORY_ID) values (2,2);
insert into BOOK_CATEGORY_RELATION (BOOK_ID,BOOK_CATEGORY_ID) values (3,1);
insert into BOOK_CATEGORY_RELATION (BOOK_ID,BOOK_CATEGORY_ID) values (4,1);
insert into BOOK_CATEGORY_RELATION (BOOK_ID,BOOK_CATEGORY_ID) values (5,1);
insert into BOOK_CATEGORY_RELATION (BOOK_ID,BOOK_CATEGORY_ID) values (6,1);
insert into BOOK_CATEGORY_RELATION (BOOK_ID,BOOK_CATEGORY_ID) values (7,1);
insert into BOOK_CATEGORY_RELATION (BOOK_ID,BOOK_CATEGORY_ID) values (8,1);
