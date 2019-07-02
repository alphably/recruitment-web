package fr.d2factory.libraryapp.member;


import fr.d2factory.libraryapp.book.BookRepository;
import fr.d2factory.libraryapp.library.Library;
import fr.d2factory.libraryapp.library.LibraryImpl;



/**
 * Student class
 */
public class Student extends Member {



	protected boolean firstYear;

	public Student() {}
	
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


}
