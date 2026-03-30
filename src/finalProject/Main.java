package finalProject;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//initializing our lib managaer class and scanner for input purposes 
		LibraryManager myManager = new LibraryManager();
		Scanner input = new Scanner(System.in);
		boolean running = true;
		
		System.out.println("Welcome to your Library Management System! ");
		
// loading users existing data
		System.out.println("Loading your library...");
		myManager.loadBooks();
		
		//loop to continue running program until user chooses otherwise
		while(running) {
			System.out.println("\n============ Main Menu ==============");
			System.out.println("Please Choose One of the Following: ");
			System.out.println("1. View Library");
			System.out.println("2. Add Book to Library (In Progress)");
			System.out.println("3. Search Book in Library (In Progress)");
			System.out.println("4. Exit Program");
			
			String choice = input.nextLine();
			
			switch (choice) {
			case "1":
				//displays library 
				myManager.displayLibrary();
				break;
				
			case "2":
				System.out.println("Feature under construction! Building 'add' logic here.");
				break;
			
			case "3":
				System.out.println("Feature under construction! Building 'search' logic here.");
				break;
			
			case "4":
				System.out.println("Thank you for using the Library Management. Goodbye!");
				running = false;
				break;
			
			default: 
				System.out.println("Invalid selction! Please try again!");
				break;
			}
			
		}
		//closing scanner
		input.close();
	}

}
