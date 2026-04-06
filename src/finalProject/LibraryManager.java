package finalProject;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException; 
/*
 * Author: Lawrence Oro
 * Class: LibraryManager.java
 * 
 * 
 * Description:
 This class manages the collection of books. 
 Using an ArrayList data structure to store our Book objects in memory 
 I chose to separate this logic from our main to make for better abstraction practice encapsulating the book collection
 ensuring that the data is only modified through my methods
 This will also allow for reusability for an app version in version 2.0 
 */
public class LibraryManager {
	//defining the data structure(ArrayList)
	private ArrayList<Book> myLibrary = new ArrayList();
	private String fileName = "books.txt"; 
	
	public void loadBooks() {
		//using scanner to read into file try-with allows for automatic closing of the scanner
		try (Scanner scanner = new Scanner(new File(fileName))){
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if(line.isEmpty()) 
					continue;
				//parsing our data to make for easy readability
				String[] data = line.split(",");
				if (data.length == 5) {
					//creating our data unit(book)
					Book tempBook = new Book(data[0].trim(), data[1].trim(), Long.parseLong(data[2].trim()),
							data[3].trim(), data[4].trim());
					//temp holding our made object
					myLibrary.add(tempBook);
				}
			}
			//handling errors if file was not found/saved 
		}catch (FileNotFoundException e) {
			System.out.println("Error: The file" + fileName + "was not found.");
		
		} catch (Exception e) {
			//using javas built in getMessage() to give user better understanding of error if file were to crash
			System.out.println("Could not load library :( ." + e.getMessage());
		}
	}
	
	public void displayLibrary() {
		System.out.println("\n--- Current Library Collection ---");
		if (myLibrary.isEmpty()) {
			System.out.println("The library is empty. Please add some books!");
		}else {
			for(Book b : myLibrary) {
				System.out.println(b.toString());
			}
		}
		System.out.println("----------------------------------------\n");
		
	}
	
	public void addBook(Book book) {
		//adding book object to myLibrary ArrayList 
		myLibrary.add(book);
		//sending feedback to user so they know function was performed successfully
		System.out.println("[Library] Succesfully Added: " + book.getTitle() + "\nBy: " + book.getAuthor() + " to your collection.");
	}
}
			
	

