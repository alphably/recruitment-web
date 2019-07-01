package fr.d2factory.libraryapp.book;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.d2factory.libraryapp.member.Member;
import fr.d2factory.libraryapp.member.Resident;
import fr.d2factory.libraryapp.member.Student;

/**
 * The book repository emulates a database via 2 HashMaps
 */
public class BookRepository {

	private Map<ISBN, Book> availableBooks = new HashMap<>();
	private Map<Book, LocalDate> borrowedBooks = new HashMap<>();
	private Map<Member, Book> borrowedBooksByMember = new HashMap<>();

	public void addBooks(List<Book> books) {
		// TODO implement the missing feature
		for (Book book : books) {

			this.availableBooks.put(book.getIsbn(), book);
		}
	}

	public Book findBook(long isbnCode) {
		// TODO implement the missing feature

		for (Map.Entry<ISBN, Book> entry : this.availableBooks.entrySet()) {

			if (entry.getKey().isbnCode == isbnCode) {
				return entry.getValue();

			}
		}

		return new Book();
	}

	public void saveBookBorrow(Book book, LocalDate borrowedAt) {
		// TODO implement the missing feature

		this.borrowedBooks.put(book, borrowedAt);
		// this.borrowedBooksByMember.put(member, book);
	}

	public LocalDate findBorrowedBookDate(Book book) {
		// TODO implement the missing feature

		return this.borrowedBooks.get(book);
	}
	
	/**
	 * 
	 */
	public boolean checkIfMemberHasBookLate(Member member) {

		LocalDate today = LocalDate.now();
		
		for(Map.Entry<Book, LocalDate> entryBorrowedBook : borrowedBooks.entrySet()) {
		
			if (member.getClass() == Student.class && ChronoUnit.DAYS.between(entryBorrowedBook.getValue(), today) > 30) {
				return true;
			} else if(member.getClass() == Resident.class && ChronoUnit.DAYS.between(entryBorrowedBook.getValue(), today) > 60) {
				return true;
			}
		}

		return false;
	}

}
