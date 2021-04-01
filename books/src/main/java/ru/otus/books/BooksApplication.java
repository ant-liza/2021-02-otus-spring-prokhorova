package ru.otus.books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BooksApplication {

	public static void main(String[] args) throws Exception {
		 SpringApplication.run(BooksApplication.class, args);

		/*AuthorDao authorDao = context.getBean(AuthorDao.class);
		System.out.println(" author count = " + authorDao.count());
		Author author = new Author(authorDao.getMaxId(),
				"Qweert","ghfuugf",null);

		authorDao.insert(author);
		System.out.println(" author count after insert = " + authorDao.count());
		System.out.println(" author list = " + authorDao.getAll());
		//authorDao.deleteById(authorDao.getMaxId());
		System.out.println(" author count after delete = " + authorDao.count());
		System.out.println(" author list = " + authorDao.getAll());
		authorDao.updateFirstNameById(authorDao.getMaxId(),  "Ivan");
		System.out.println(" author list = " + authorDao.getAll());*/

		//Console.main(args);

		//Test for bookCategory
		/*BookCategoryDao bookCategoryDao = context.getBean(BookCategoryDao.class);
		System.out.println(" COUNT book categories = " + bookCategoryDao.count());
		BookCategory bookCategory = new BookCategory(0,"Русская классика");
		bookCategoryDao.insert(bookCategory);
		System.out.println(" COUNT AFTER insert new book category = " + bookCategoryDao.count());
		List<BookCategory> allBC = bookCategoryDao.getAll();
		System.out.println(" ALL BOOK CATEGORIES:");
		allBC.forEach(System.out::println);
		System.out.println(" GET BY ID:" + bookCategoryDao.getById(3));
		bookCategoryDao.updateBookCategoryNameById(3,"Зарубежная классика");
		System.out.println(" AFTER UPDATE = " );
		bookCategoryDao.getAll().forEach(System.out::println);
		bookCategoryDao.deleteById(3);
		System.out.println(" COUNT AFTER DELETE = " + bookCategoryDao.count());
		bookCategoryDao.getAll().forEach(System.out::println);*/

		//Test for book
		/*BookDao bookDao = context.getBean(BookDao.class);
		System.out.println(" COUNT book  = " + bookDao.count());
		Book book = new Book(0,2,1,"Песнь Сюзанны");
		bookDao.insert(book);
		System.out.println(" COUNT AFTER insert new book  = " + bookDao.count());
		List<Book> allBooks = bookDao.getAll();
		System.out.println(" ALL BOOKS:");
		allBooks.forEach(System.out::println);
		System.out.println(" GET BY ID:" + bookDao.getById(3));
		bookDao.updateBookDescriptionById(3,"Волки Кальи");
		System.out.println(" AFTER UPDATE = " );
		bookDao.getAll().forEach(System.out::println);
		bookDao.deleteById(3);
		System.out.println(" COUNT AFTER DELETE = " + bookDao.count());
		bookDao.getAll().forEach(System.out::println);*/


	}

}
