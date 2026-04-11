package finalProject;
/*
 * Author: Lawrence Oro 
 * Class: MenuConstants.java
 * 
 * Description: 
 * This class stores all of the static text for the UI. 
 * Centralizing these strings rather than leaving in main
 * demonstrates better Abstraction by separating the view of our program 
 * to the logic.*/
public class MenuConstants {
	public static final String WELCOME_MSG = "Welcome to your Library Management System!";
	public static final String MAIN_MENU = "\n============ Main Menu ==============\n" +
											"Please Choose One of the Following: \n" +
											"1. View Library\n" +
											"2. Add Book to Library\n" +
											"3. Delete Book from Library\n" + 
											"4.Undo the Last Deletion\n" +
											"5. Search Book in Library\n" +
											"6. Exit Program";
	
	public static final String EXIT_MSG = "Thank you for using the Library Manager. Goodbye!";
	public static final String INVALID_CHOICE = "[Error] Invalid Selection! Please try again.";
}
