package finalProject;
/*
 * Author: Lawrence Oro
 * Class: LibraryException.java
 * 
 * Description: 
 * A custom exception class that extends the base Exception class 
 * This is used to handle library-specific errors (missing books and such)
 * demonstrating advanced Object-Oriented design using try-catch blocks. 
 * */
public class LibraryException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LibraryException(String message) {
		super(message);
	}

}
