package finalProject;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException; 
import java.io.PrintWriter;
import java.util.Stack;
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
	private ArrayList<Book> myLibrary = new ArrayList<>();
	private String fileName = "books.txt"; 
	private Stack<Book> undoStack = newStack<>();
	
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
					Book tempBook = new Book(data[0].trim(), data[1].trim(), 
							Long.parseLong(data[2].trim()),
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
		ArrayList<Book> left = new ArrayList<>();
		ArrayList<Book> right = new ArrayList<>();
		
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
	/*
	 * Author: Lawrence Oro
	 * Algorithm: Binary Search
	 * Complexity: 0(log n)
	 * 
	 * Description: This method searches for a book inside collection by its title.
	 * Since the library is sorted alphabetically, we check the middle element and 
	 * eliminate half of the remaining books in our collection with every comparison. */
	public Book searchByTitle(String targetTitle) {
		//ensuring list is sorted before searching 
		sortLibrary(myLibrary);
		
		int low = 0;
		int high = myLibrary.size() - 1;
		
		while(low <= high) {
			int mid = (low + high) / 2;
			String midTitle = myLibrary.get(mid).getTitle();
			
			int comparison = midTitle.compareToIgnoreCase(targetTitle);
			
			if(comparison == 0) {
				//found title
				return myLibrary.get(mid);
			}
			else if(comparison < 0) {
				//searching right half of list
				low = mid + 1;
			}
			else {
				//searching left half of list
				high = mid - 1;
			}
		}
		//didnt find title
		return null;
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
		//immediately persisting our data to the csv file in case of crashes
		saveBooks();
		System.out.println("[Library] Succesfully Added: " + book.getTitle() + "\nBy: " + book.getAuthor() + " to your collection.");
	}
	/*Author: Lawrence Oro 
	 * 
	 * This method takes every Book object in myLibrary ArrayList 
	 * and writes it into 'books.txt' file. This ensures that books
	 * added are saved even after session is closed and, loaded once started again.
	 * Using our csvFormat() method from the Book class ensures file stays in 
	 * the format that our loadBooks() method can read*/
	
	public void saveBooks() {
		try(PrintWriter writer = new PrintWriter(new File(fileName))){
			for(Book b : myLibrary) {
				//using csv format defined in our Book class
				writer.println(b.csvFormat());
			}
		} catch(IOException e) {
			System.out.println("[System Error] Could not save to file: " + e.getMessage());
		}
	}
	/*
	 * Author: Lawrence Oro
	 * Function: deleteBook(String title)
	 * 
	 * Description: Searches the ArrayList for a book with matching title
	 * and removes it from the list. After deletion calling saveBooks() updates the text file.*/
	public boolean deleteBook(String title) {
		//using for loop to search for the title 
		for(int i = 0; i < myLibrary.size(); i++) {
			//compareToIgnoreCase to ensure non case-sensitivity
			if(myLibrary.get(i).getTitle().equalsIgnoreCase(title)) {
				//Push: saving the book object into the stack before removal
				undoStack.push(myLibrary.get(i));
				myLibrary.remove(i);
				//saving remaining list to the file to ensure persistence
				saveBooks();
				//successfully found and removed
				return true;
			}
		}
		//book title was not found
		return false;
	}
	
	/*
	 * Author: Lawrence Oro
	 * DataStructure: Stack (java.util.Stack)
	 * Algorithm Complexity: 0(1) - Constant Time
	 * Description: This method implements an "undo" feature for deleted books using a stack
	 * When a book is removed via deleteBook(), it is then 'pushed' onto the undo stack. This method will then pop
	 * that book off the top of the stack and re-adds it to the library.*/
	public void undoDelete() {
		if (!undoStack.isEmpty()) {
			//pop: taking the 'top' book off the stack (the last book deleted)
			Book restoredBook = undoStack.pop(); 
			//adding the book back into the main arraylist
			myLibrary.add(restoredBook);
			//persisting the change so the book is saved within the txt file
			saveBooks();
			
			System.out.println("[Library] Undo Successful! Restored: " + restoredBook.getTitle());
		} else {
			System.out.println("[Error] There are no deleted book to restore.");
		}
	}
}
			
	

