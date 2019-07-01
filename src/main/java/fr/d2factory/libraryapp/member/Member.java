package fr.d2factory.libraryapp.member;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.book.BookRepository;
import fr.d2factory.libraryapp.library.HasLateBooksException;
import fr.d2factory.libraryapp.library.Library;

/**
 * A member is a person who can borrow and return books to a {@link Library} A
 * member can be either a student or a resident
 */
public abstract class Member {
	/**
	 * An initial sum of money the member has
	 */
	protected float wallet;

	private BookRepository bookRepo;

	public void setBookRepo(BookRepository bookRepo) {
		this.bookRepo = bookRepo;
	}

	/**
	 * The member should pay their books when they are returned to the library
	 *
	 * @param numberOfDays the number of days they kept the book
	 */
	public abstract void payBook(int numberOfDays);

	public float getWallet() {
		return wallet;
	}

	public void setWallet(float wallet) {
		this.wallet = wallet;
	}
	

	public Book borrowBook(long isbnCode, LocalDate borrowedAt) throws HasLateBooksException {
		// TODO Auto-generated method stub

		// check if member has late books
		if (bookRepo.checkIfMemberHasBookLate(this)) {
			throw new HasLateBooksException("Vous avez un livre ou plusieurs livres en retard");
		}
		// search if the book is available in library
		Book book = bookRepo.findBook(isbnCode);

		// borrowed the book
		bookRepo.saveBookBorrow(book, borrowedAt);

		return book;
	}


	public void returnBook(Book book, Member member) {
		// TODO Auto-generated method stub

		LocalDate dateBorrowed = bookRepo.findBorrowedBookDate(book);
		LocalDate today = LocalDate.now();

		long numberKeepBook = ChronoUnit.DAYS.between(dateBorrowed, today);

		this.payBook((int) numberKeepBook);

	}

}
