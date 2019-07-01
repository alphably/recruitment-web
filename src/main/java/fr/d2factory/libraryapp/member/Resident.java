package fr.d2factory.libraryapp.member;

import java.time.LocalDate;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.library.HasLateBooksException;
import fr.d2factory.libraryapp.library.Library;

/**
 *  Resident class
 */
public class Resident extends Member implements Library{
    
    /**
     * The member should pay their books when they are returned to the library
     *
     * @param numberOfDays the number of days they kept the book
     */
    public void payBook(int numberOfDays) {
    	if(numberOfDays <= 60){
    		this.wallet = 10 * numberOfDays;
    	}else {
    		this.wallet = (10 * 60) + (20 *(numberOfDays - 60));
    	}
    }

	@Override
	public Book borrowBook(long isbnCode, LocalDate borrowedAt) throws HasLateBooksException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void returnBook(Book book, Member member) {
		// TODO Auto-generated method stub
		
		// calculate delay borrow book
		
	}

}
