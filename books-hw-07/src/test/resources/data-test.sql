insert into authors (FIRSTNAME,LASTNAME,NICKNAME) values ('Agatha','Christie',null);
insert into authors (FIRSTNAME,LASTNAME,NICKNAME) values ('Stephen','King',null);
insert into authors (FIRSTNAME,LASTNAME,NICKNAME) values ('George','Martin',null);

insert into book_categories (BOOK_CATEGORY_NAME) values ('Фантастика');
insert into book_categories (BOOK_CATEGORY_NAME) values ('Детектив');
insert into book_categories (BOOK_CATEGORY_NAME) values ('TEST');

insert into books (AUTHOR_ID,TITLE) values (1,'Десять негритят');
insert into books (AUTHOR_ID,TITLE) values (1,'Вилла Белый Конь');
insert into books (AUTHOR_ID,TITLE) values (2,'Стрелок');
insert into books (AUTHOR_ID,TITLE) values (2,'Извлечение троих');
insert into books (AUTHOR_ID,TITLE) values (2,'Мистер Мерседес');
insert into books (AUTHOR_ID,TITLE) values (2,'Кто нашел, берет себе');
insert into books (AUTHOR_ID,TITLE) values (2,'Пост сдал');
insert into books (AUTHOR_ID,TITLE) values (3,'Игра престолов');


insert into book_category_relation (BOOK_ID,BOOK_CATEGORY_ID) values (1,2);
insert into book_category_relation (BOOK_ID,BOOK_CATEGORY_ID) values (2,2);
insert into book_category_relation (BOOK_ID,BOOK_CATEGORY_ID) values (3,1);
insert into book_category_relation (BOOK_ID,BOOK_CATEGORY_ID) values (4,1);
insert into book_category_relation (BOOK_ID,BOOK_CATEGORY_ID) values (5,1);
insert into book_category_relation (BOOK_ID,BOOK_CATEGORY_ID) values (6,1);
insert into book_category_relation (BOOK_ID,BOOK_CATEGORY_ID) values (7,1);
insert into book_category_relation (BOOK_ID,BOOK_CATEGORY_ID) values (8,1);

insert into notes(book_id, comment) values (1,'good');
insert into notes(book_id, comment) values (1,'bad');
insert into notes(book_id, comment) values (1,'medium');
insert into notes(book_id, comment) values (2,'good');

