package fr.d2factory.libraryapp.library;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.book.BookRepository;

import fr.d2factory.libraryapp.member.Resident;
import fr.d2factory.libraryapp.member.Student;
import fr.d2factory.libraryapp.utils.Utilitaire;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import java.util.List;

public class LibraryTest {
	private Library library;
	private BookRepository bookRepository;

	@SuppressWarnings({ "unused", "unchecked" })
	@Before
	public void setup() {
		// TODO instantiate the library and the repository

		bookRepository = new BookRepository();
		List<Book> books = new ArrayList<Book>();

		// TODO add some test books (use BookRepository#addBooks)
		// TODO to help you a file called books.json is available in src/test/resources
		books = Utilitaire.jsonFileParser(new File("src/test/resources/books.json"));

		bookRepository.addBooks(books);

	}

	@Test
	public void member_can_borrow_a_book_if_book_is_available() {
		long isbnCode = 968787565445l;

		Student student = null;
		student = Utilitaire.CreateStudent();

		// bookRepository.checkIfMemberHasBookLate(student);

		//
		Book book = bookRepository.findBook(isbnCode);

		student.setBookRepo(bookRepository);

		LocalDateTime currentTime = LocalDateTime.now();
		LocalDate today = currentTime.toLocalDate();

		// library = student;

		Book bookBorrowed = student.borrowBook(isbnCode, today);
		// fail("Implement me");

		Assert.assertEquals(book, bookBorrowed);

	}

	@Test(expected=NullPointerException.class)
	public void borrowed_book_is_no_longer_available() {
		long isbnCode = 968787565445l;

		Student student, anotherStudent = null;
		student = Utilitaire.CreateStudent();
		anotherStudent = Utilitaire.CreateStudent();
		// bookRepository.checkIfMemberHasBookLate(student);

		//
		Book book = bookRepository.findBook(isbnCode);

		student.setBookRepo(bookRepository);

		LocalDate borrowDate = LocalDate.of(2019, Month.JUNE , 30);

		Book bookBorrowed = student.borrowBook(isbnCode, borrowDate);
		Book sameBookBorrowed = anotherStudent.borrowBook(isbnCode, LocalDate.now());
		
		
	}

	@Test
	public void residents_are_taxed_10cents_for_each_day_they_keep_a_book() {
		// fail("Implement me");
		Resident res = new Resident();
		res.payBook(60);

		Assert.assertNotEquals(60f, res.getWallet());

	}

	@Test
	public void students_pay_10_cents_the_first_30days() {
		Student st = new Student();
		st.setFirstYear(false);
		st.payBook(30);

		Assert.assertEquals(300f, st.getWallet(), 0.01);

		// fail("Implement me");
	}

	@Test
	public void students_in_1st_year_are_not_taxed_for_the_first_15days() {
		Student st = new Student();
		st.setFirstYear(true);
		st.payBook(30);

		Assert.assertEquals(150f, st.getWallet(), 0.01);
		// fail("Implement me");
	}

	@Test
	public void students_pay_15cents_for_each_day_they_keep_a_book_after_the_initial_30days() {
		long isbnCode = 968787565445l;

		Student student = null;
		student = Utilitaire.CreateStudent();

		// bookRepository.checkIfMemberHasBookLate(student);

		//
		Book book = bookRepository.findBook(isbnCode);

		student.setBookRepo(bookRepository);

		LocalDate borrowDate = LocalDate.of(2019, Month.MAY, 25);

		Book bookBorrowed = student.borrowBook(isbnCode, borrowDate);
		student.returnBook(bookBorrowed, student);
		
		Assert.assertEquals(405.0, student.getWallet(), 0.01);
		
	}

	@Test
	public void residents_pay_20cents_for_each_day_they_keep_a_book_after_the_initial_60days() {
		// fail("Implement me");
		Resident res = new Resident();
		res.payBook(61);

		Assert.assertEquals(620f, res.getWallet(), 0.01);

	}

	@Test(expected=HasLateBooksException.class)
	public void members_cannot_borrow_book_if_they_have_late_books() {
		long isbnCode = 968787565445l;

		Student student = null;
		student = Utilitaire.CreateStudent();

		// bookRepository.checkIfMemberHasBookLate(student);

		//
		Book book = bookRepository.findBook(isbnCode);

		student.setBookRepo(bookRepository);

		LocalDate borrowDate = LocalDate.of(2019, Month.MAY, 25);

		Book bookBorrowed = student.borrowBook(isbnCode, borrowDate);
		//student.returnBook(bookBorrowed, student);
		// another borrow
		Book bookBorrowedSecond = student.borrowBook(3326456467846l, LocalDate.now());

	}
}
