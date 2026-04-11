package finalProject;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
//initializing our lib managaer class and scanner for input purposes 
		LibraryManager myManager = new LibraryManager();
		Scanner input = new Scanner(System.in);
		boolean running = true;
		
		System.out.println("Welcome to your Library Management System! ");
		
// loading users existing data
		System.out.println("Loading your library...");
		myManager.loadBooks();
		
		// while loop to continue running program continuously prompting the user to make a selection from the menu 
		//ending only when user chooses to
		while(running) {
			System.out.println("\n============ Main Menu ==============");
			System.out.println("Please Choose One of the Following: ");
			System.out.println("1. View Library");
			System.out.println("2. Add Book to Library");
			System.out.println("3. Delete Book from Library");
			System.out.println("4. Undo the Last Deletion");
			System.out.println("5. Search Book in Library");
			System.out.println("6. Exit Program");
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
				System.out.println("Enter Title: ");
				String title = input.nextLine();
				
				System.out.println("Enter Author: ");
				String author = input.nextLine();
				
				System.out.print("Enter ISBN: ");
				
				//using try-catch to ensure user is inputing an integer
				//converting inputed String to 'long' to match our book class instance
				//temp placeholder for isbn before sending to the constructor
				long isbn;
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
				Book newBook = new Book(title, author, isbn, genre, status);
				
				//adding to the ArrayList(myLibrary after creation)
				myManager.addBook(newBook);
				break;
				
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
				
			case "4":
				myManager.undoDelete();
				break;
				
			//performs search function
			case "5":
				System.out.println("\n--- Search Library ---");
				System.out.print("Enter the title of the book you're looking for: ");
				
				String searchTitle = input.nextLine();
				
				Book foundBook = myManager.searchByTitle(searchTitle);
				
				if(foundBook != null) {
					System.out.println("\n[Success] Book found in your collection:");
					System.out.println(foundBook.toString());
				} else {
					System.out.println("\n[Notice] '" + searchTitle + "' is not in your library.");
				}
				break;
			//closes/kills program
			case "6":
				System.out.println("Thank you for using the Library Management. Goodbye!");
				running = false;
				break;
			//default handles if user were to choose anything other than the given options
			default: 
				System.out.println("Invalid selction! Please try again!");
				break;
			}
			
		}
		//closing scanner resource
		input.close();
	}

}
