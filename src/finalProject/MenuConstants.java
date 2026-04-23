package finalProject;
/*
 * Author: Lawrence Oro 
 * Class: MenuConstants.java
 * 
 * Description: 
 * This class centralizes all UI strings. By separating 
 * the 'View' (text) from the 'Controller' (logic), 
 * in doing so we demonstrate high-level abstraction 
 * and make for easy software to maintain or reuse in future versions.*/
public class MenuConstants {
	public static final String WELCOME_MSG = "Welcome to your Library Management System!";
	public static final String MAIN_MENU = "\n============ Main Menu ==============\n" +
											"Please Choose One of the Following: \n" +
											"1. View Library\n" +
											"2. Add Book to Library\n" +
											"3. Delete Book from Library\n" + 
											"4. Undo the Last Deletion\n" +
											"5. Search Book in Library\n" +
											"6. Exit Program";
	
	public static final String EXIT_MSG = "Thank you for using the Library Manager. Goodbye!";
	public static final String INVALID_CHOICE = "[Error] Invalid Selection! Please try again.";
}
