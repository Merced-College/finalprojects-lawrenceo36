package finalProject;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException; 

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
}
			
	

