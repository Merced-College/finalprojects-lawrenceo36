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
	
	public void sortLibrary(ArrayList<Book> list) {
		//if list has 0 or 1 element, list is already sorted 
		if(list.size() <= 1) {
			return;
		}
		// finding mid point of our list to allow for splitting of library
		int mid = list.size() / 2;
		ArrayList<Book> left = new ArrayList();
		ArrayList<Book> right = new ArrayList();
		
		//diving our library into two halves 
		for(int i = 0; i < mid; i++) {
			left.add(list.get(i));
		}
		
		for(int i = mid; i < list.size(); i++) {
			right.add(list.get(i));
		}
		
		//implementing recursion to sort both sublists of our library calling sortLibrary
		sortLibrary(left);
		sortLibrary(right);
		
		//merging our sorted sublists back into our original list
		merge(list, left, right);
	}
	
	private void merge(ArrayList<Book> list, ArrayList<Book> left, ArrayList<Book> right) {
		int leftIndex = 0, rightIndex = 0, listIndex = 0;
		
		//compare our titles, placing the 'smaller' book in our list first (alphabetically)
		while(leftIndex < left.size() && rightIndex < right.size()) {
			//calling getTitle() to access the data that was encapsulated in the Book object
			if(left.get(leftIndex).getTitle().compareToIgnoreCase(right.get(rightIndex).getTitle()) <= 0) {
				list.set(listIndex++ ,  left.get(leftIndex++));
			} else {
				list.set(listIndex++,  right.get(rightIndex++));
			}
		}
		//copying the remaining elements from the sublist on the left
		while(leftIndex < left.size()) {
			list.set(listIndex++, left.get(leftIndex++));
		}
		//copying the remaining elements from the sublist on the right
		while(rightIndex < right.size()) {
			list.set(listIndex++,  right.get(rightIndex++));
		}
		
	}
	
	public void displayLibrary() {
		System.out.println("\n--- Current Library Collection ---");
		
		if (myLibrary.isEmpty()) {
			System.out.println("The library is empty. Please add some books!");
		}else {
			//calling our sorting algo (0(n log n) before printing
			sortLibrary(myLibrary);
			//using the books toString() to display formatted data
			for(Book b : myLibrary) {
				System.out.println(b.toString());
				System.out.println("----------------------------------------\n");
			}
		}
	}
	
	public void addBook(Book book) {
		//adding book object to myLibrary ArrayList 
		myLibrary.add(book);
		//sending feedback to user so they know function was performed successfully
		System.out.println("[Library] Succesfully Added: " + book.getTitle() + "\nBy: " + book.getAuthor() + " to your collection.");
	}
	
	public boolean deleteBook(String title) {
		//using for loop to search for the title 
		for(int i = 0; i < myLibrary.size(); i++) {
			//compareToIgnoreCase to ensure non case-sensitivity
			if(myLibrary.get(i).getTitle().equalsIgnoreCase(title)) {
				myLibrary.remove(i);
				//saving remaining list to the file to ensure persistence
				//saveBooks();
				//successfully found and removed
				return true;
			}
		}
		//book title was not found
		return false;
	}
}
			
	

