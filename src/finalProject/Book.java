package finalProject;

public class Book {
	//instance variables for book
	private String title;
	private String author;
	private long ISBN;
	private String genre;
	private String status;
	//default constructor
	public Book() {
		this.title = "Unknown";
		this.author = "Unknown";
		this.ISBN = 123;
		this.genre = "Unknown";
		this.status = "Unknown";
	}
	
	//parameterized constructor
public Book(String title, String author, long ISBN, String genre, String status) {
	this.title = title;
	this.author = author;
	this.ISBN = ISBN;
	this.genre = genre;
	this.status = status;
}

//getters and setters
public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getAuthor() {
	return author;
}

public void setAuthor(String author) {
	this.author = author;
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

public String getStatus() {
	 return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String csvFormat() {
	return title + "," + author + "," +  ISBN + "," + genre + "," + status; 
}

public String toString() {
	return "Title: " + title + "Author: " + author + "\nISBN: " + ISBN + "\nGenre: " + genre + "\nStatus: " + status;

}
}
