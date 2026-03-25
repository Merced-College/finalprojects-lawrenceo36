package finalProject;

public class Book {
	//instance variables for book
	private String title;
	private long ISBN;
	private String genre;
	
	//default contructor
	public Book() {
		this.title = "Unknown";
		this.ISBN = 123;
		this.genre = "Unknown";
	}
	
	//parameterized constructor
public Book(String title, long ISBN, String genre) {
	this.title = title;
	this.ISBN = ISBN;
	this.genre = genre;
}

//getters and setters
public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public long getISBN() {
	return ISBN;
}

public void setISBN(long ISBN) {
	this.ISBN = ISBN;
}

public String getGenre() {
	return genre;
}

public void setGenre(String genre) {
	this.genre = genre;
}

}
