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
			System.out.println("2. Add Book to Library (In Progress)");
			System.out.println("3. Search Book in Library (In Progress)");
			System.out.println("4. Exit Program");
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
				System.out.println("Feature under construction! Building 'add' logic here.");
				break;
			//performs search function
			case "3":
				System.out.println("Feature under construction! Building 'search' logic here.");
				break;
			//closes/kills program
			case "4":
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
