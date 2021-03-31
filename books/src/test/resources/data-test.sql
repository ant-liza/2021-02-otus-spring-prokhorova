insert into authors (FIRSTNAME,LASTNAME,NICKNAME) values ('Agatha','Christie',null);
insert into authors (FIRSTNAME,LASTNAME,NICKNAME) values ('Stephen','King',null);


insert into book_categories (BOOK_CATEGORY_NAME) values ('Фантастика');
insert into book_categories (BOOK_CATEGORY_NAME) values ('Детектив');

insert into books (AUTHOR_ID,BOOK_CATEGORY_ID,DESCRIPTION) values (1,2,'Десять негритят');
insert into books (AUTHOR_ID,BOOK_CATEGORY_ID,DESCRIPTION) values (1,2,'Вилла Белый Конь');
insert into books (AUTHOR_ID,BOOK_CATEGORY_ID,DESCRIPTION) values (2,1,'Стрелок');
insert into books (AUTHOR_ID,BOOK_CATEGORY_ID,DESCRIPTION) values (2,1,'Извлечение троих');
insert into books (AUTHOR_ID,BOOK_CATEGORY_ID,DESCRIPTION) values (2,2,'Мистер Мерседес');
insert into books (AUTHOR_ID,BOOK_CATEGORY_ID,DESCRIPTION) values (2,2,'Кто нашел, берет себе');
insert into books (AUTHOR_ID,BOOK_CATEGORY_ID,DESCRIPTION) values (2,2,'Пост сдал');

