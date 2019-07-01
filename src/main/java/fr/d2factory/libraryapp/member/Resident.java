package fr.d2factory.libraryapp.member;




/**
 * Resident class
 */
public class Resident extends Member {


	/**
	 * The member should pay their books when they are returned to the library
	 *
	 * @param numberOfDays the number of days they kept the book
	 */
	public void payBook(int numberOfDays) {
		if (numberOfDays <= 60) {
			this.wallet = 10 * numberOfDays;
		} else {
			this.wallet = (10 * 60) + (20 * (numberOfDays - 60));
		}
	}

	}
