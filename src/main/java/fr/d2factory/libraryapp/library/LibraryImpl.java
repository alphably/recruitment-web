package fr.d2factory.libraryapp.library;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.book.BookRepository;
import fr.d2factory.libraryapp.member.Member;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * The library class is in charge of stocking the books and managing the return delays and members
 *
 * The books are available via the {@link fr.d2factory.libraryapp.book.BookRepository}
 */
public class LibraryImpl implements Library {
	
	private BookRepository bookRepo;

	public LibraryImpl(BookRepository BookRepository) {
		this.bookRepo = BookRepository;
	}
	
	


	public Book borrowBook(long isbnCode, Member member, LocalDate borrowedAt) throws HasLateBooksException {
		// TODO Auto-generated method stub

		// check if member has late books
		if (bookRepo.checkIfMemberHasBookLate(member)) {
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

		member.payBook((int)numberKeepBook);

	}}
