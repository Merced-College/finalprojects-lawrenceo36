package finalProject;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
//initializing our lib managaer class and scanner for input purposes 
		LibraryManager myManager = new LibraryManager();
		Scanner input = new Scanner(System.in);
		boolean running = true;
		
		System.out.println(MenuConstants.WELCOME_MSG);
// loading users existing data
		System.out.println("Loading your library...");
		myManager.loadBooks();
		
		// while loop to continue running program continuously prompting the user to make a selection from the menu 
		//ending only when user chooses to
		while(running) {
			System.out.println(MenuConstants.MAIN_MENU);
			System.out.print("> Selection: ");
			// reading the users input
			String choice = input.nextLine();
			
			// switch case statements to display the selected menu option relative to the users input
			switch (choice) {
			
			case "1":
				//displays library 
				myManager.displayLibrary();
				break;
				
			//performs add function.
			case "2":
				System.out.println("\n --- Add a New Book ---");
				
				//collecting data(user input) from terminal
				System.out.print("Enter Title: ");
				String title = input.nextLine();
				
				System.out.print("Enter Author: ");
				String author = input.nextLine();
				
				//using try-catch to ensure user is inputing an integer
				//converting inputed String to 'long' to match our book class instance
				//temp placeholder for isbn before sending to the constructor
				System.out.print("Enter ISBN: ");
				long isbn = input.nextLong();
				input.nextLine();
				
				try { 
					isbn  = Long.parseLong(input.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("[Error] Invalid ISBN format. Input INTEGER Values Only");
					//will prevent program form crashing if user inputs a non-integer value
					isbn = 0; 
				}
				
				System.out.print("Enter Genre: ");
				String genre = input.nextLine();
				
				System.out.print("Enter status (e.g., Reading, Finished, Owned): ");
				String status = input.nextLine();
				
				//creating the newly added book object using parameterized constructor
				//and adding it into the list
				myManager.addBook(new Book(title, author, isbn, genre, status));
				
				//adding to the ArrayList(myLibrary after creation)
				System.out.println("\n[Library] Success! '" + title + "' has been added to collection.");
				break;
				
			//deletes book from library
			case "3":
				System.out.println("\n--- Delete a Book ---");
				System.out.print("Enter the title of the book to remove: ");
				String titleToDelete = input.nextLine();
				
				//calling manager to store results 
				boolean deleted = myManager.deleteBook(titleToDelete);
				
				if(deleted) {
					System.out.println("[Library] Successfully removed: " + titleToDelete);
				} else {
					System.out.println("[Error] Could not find a book with that title.");
				}
				break;
				
			//performs undo for accidental deletion	
			case "4":
				myManager.undoDelete();
				System.out.println("\n[Library] Last deletion undone.");
				break;
				
			//performs search function
			case "5":
				System.out.println("\n--- Search Library ---");
				System.out.print("Enter the title of the book you're looking for: ");
				String searchTitle = input.nextLine();
				
				try {
					Book foundBook = myManager.searchByTitle(searchTitle);
					System.out.println("\n[Success] Found: " + foundBook.toString());
				} catch (LibraryException e) {
					//catches error message in LibraryManger.java
					System.out.println("\n[Error]" + e.getMessage());
				}
				break;
				
			//closes/kills program
			case "6":
				System.out.println(MenuConstants.EXIT_MSG);
				running = false;
				break;
			//default handles if user were to choose anything other than the given options
			default: 
				System.out.println(MenuConstants.INVALID_CHOICE);
				break;
			}
			
		}
		//closing scanner resource
		input.close();
	}

}
