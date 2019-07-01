package fr.d2factory.libraryapp.member;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.book.BookRepository;
import fr.d2factory.libraryapp.library.HasLateBooksException;
import fr.d2factory.libraryapp.library.Library;

/**
 * Student class
 */
public class Student extends Member implements Library {

	
	private BookRepository bookRepo;
	
	public void setBookRepo(BookRepository bookRepo) {
		this.bookRepo = bookRepo;
	}

	protected boolean firstYear;

	public Student() {
	}
	
	
	public boolean isFirstYear() {
		return firstYear;
	}

	public void setFirstYear(boolean firstYear) {
		this.firstYear = firstYear;
	}

	/**
	 * The member should pay their books when they are returned to the library
	 *
	 * @param numberOfDays the number of days they kept the book
	 */
	public void payBook(int numberOfDays) {
		if (this.isFirstYear()) {
			
			if (numberOfDays <= 30) {
				this.setWallet(10 * numberOfDays/2.f);
			}
			
		} else {
			if (numberOfDays <= 30) {
				this.setWallet(10 * numberOfDays);
			}else {
				this.setWallet(10 * 30 + 15 * (numberOfDays - 30));
			}
		}
	}

	@Override
	public Book borrowBook(long isbnCode, LocalDate borrowedAt) throws HasLateBooksException {
		// TODO Auto-generated method stub
		
		// check if member has late books
		 if(bookRepo.checkIfMemberHasBookLate(this)) {
			 throw new HasLateBooksException("Vous avez un livre ou plusieurs livres en retard");
		 }
		// search if the book  is available in library
		Book book = bookRepo.findBook(isbnCode);
		
		// borrowed the book
		bookRepo.saveBookBorrow(book, borrowedAt);

		return book;
	}

	@Override
	public void returnBook(Book book, Member member) {
		// TODO Auto-generated method stub

		LocalDate dateBorrowed = bookRepo.findBorrowedBookDate(book);
		LocalDate today = LocalDate.now();
		
		long numberKeepBook = ChronoUnit.DAYS.between(dateBorrowed, today);
		
		this.payBook((int)numberKeepBook);

	}

}
