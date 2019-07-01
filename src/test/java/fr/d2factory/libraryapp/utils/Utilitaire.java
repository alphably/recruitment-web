package fr.d2factory.libraryapp.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.book.ISBN;
import fr.d2factory.libraryapp.member.Student;

public class Utilitaire {

	public  static Student CreateStudent() {
		Student student = new Student();
		student.setFirstYear(false);
		student.setWallet(0);
		
		
		return student;
		
	}
	
	
	public static List<Book> jsonFileParser(File file){
		
		List<Book> books = new ArrayList<Book>();
        
		   
	       JSONParser jsonParser = new JSONParser();
	        
	       try (FileReader reader = new FileReader( new File("src/test/resources/books.json")))
	       {
	           //Read JSON file
	           Object obj = jsonParser.parse(reader);

	           JSONArray bookList = (JSONArray) obj;
	          // System.out.println(bookList);
	           
	           for (int i = 0; i < bookList.size(); i++) {
	        	   JSONObject bookJson = (JSONObject) bookList.get(i);
	        	   Book book = new Book();
	        	
	        	   String title = (String) bookJson.get("title");
	        	   book.setTitle(title);
	        	   
	        	   String author = (String) bookJson.get("author");
	        	   book.setAuthor(author);
	        	   
	               JSONObject isbnObject = (JSONObject) bookJson.get("isbn");
	               long isbnCode = (long) isbnObject.get("isbnCode");
	               
	        	   ISBN isbn = new ISBN(isbnCode);
	        	   book.setIsbn(isbn);
	        	   
	        	   books.add(book);
	        	}
	           
	           //bookRepository.addBooks(books);
	           return books;

	       } catch (FileNotFoundException e) {
	           e.printStackTrace();
	       } catch (IOException e) {
	           e.printStackTrace();
	       } catch (org.json.simple.parser.ParseException e) {
	           e.printStackTrace();
	       }
		
		return null;
	}
}
