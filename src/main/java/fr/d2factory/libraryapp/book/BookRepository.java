package fr.d2factory.libraryapp.book;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The book repository emulates a database via 2 HashMaps
 */
public class BookRepository {
    private Map<ISBN, Book> availableBooks = new HashMap<>();
    private Map<Book, LocalDate> borrowedBooks = new HashMap<>();

    public void addBooks(List<Book> books){
        //TODO implement the missing feature
    	for (Book book : books) {
		
    		this.availableBooks.put(book.getIsbn(), book);
		}
    }

    public Book findBook(long isbnCode) {
        //TODO implement the missing feature
    	
    	Book book = null;
    	
    	book = this.availableBooks.get(new ISBN(isbnCode));
    	
        return book;
    }

    public void saveBookBorrow(Book book, LocalDate borrowedAt){
        //TODO implement the missing feature
    	
    	this.borrowedBooks.put(book, borrowedAt);
    }

    public LocalDate findBorrowedBookDate(Book book) {
        //TODO implement the missing feature
    	
        return this.borrowedBooks.get(book);
    }
    
}
