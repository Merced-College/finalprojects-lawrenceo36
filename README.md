[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-2972f46106e565e64193e422d61a12cf1da4916b45550586e14ef0a7c637dd04.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=23252719)
# cpsc39-finalProjects

Upload your final project to this github repo.

Make a README file for your project and put the infor about your project in it - your name, date and what your program does.

#Project Info
Name: Lawrence Oro 

Date: 3-23-26

I will be building a library management system where the user will be able to allocate their books to a digital library database where they will be allowed to add/delete titles. They will be allowed to search within their own libraries making for easy lookup/management of any specific title. The
program will also contain sorting to help with searching of data. Lastly they will also be able to search a larger database which will be implemented to help the user find the next book to read. This program frees the user of the mental burden of having to remember each and every title that they own, and it makes for easy discovery of new titles.

# Library Management System Features 
1. (Add & Delete Books) Easily add new books (Title, Author, ISBN, Genre, Status) or remove them from your collection 

2. (Search Library) Quickly look up books by title

3. (Undo Functionality) Accidentally deleted a book? Use the Undo feature to instantly restore the last deleted item

4. (Persistent Storage) The library automatically saves to and loads from a local 'books.txt' file, ensuring no data is lost between sessions.

5.(Robust Error Handling) Custom exception handling ('LibraryException') and input validation preventing the program from crashing 

#Data Structures Used 

(ArrayList) Used to store the primary collection of Book objects in memory for dynamic resizing

(Stack) Implemented to handle the Undo functionality (Last-In, First-Out) for deleted data(books).

(Arrays & Strings) Extensively used for data parsing (CSV format) and title comparisons

#Algorithms Used 

(Linear Search) Used to locate books by title within the ArrayList (case-insensitivity) 

(Binary Search) Powers the " Search " feature. Because the library is sorted via Merge Sort, this algorithm efficiently finds a specific book by repeatedly dividing the list in half, operating at an O(log n) time comp. 

(Merge Sort(RECURSIVE)) Automatically sorts the library alphabetically by title before it is displayed to the user. This divide-and-conquer algorithm operates at an O(n log n ) time complexity. 

#How to Run the Program 
1.Open your terminal.
2. Navigate to the 'src' folder containing the project files.
3. Compile the Java files by running: '''bash
javac finalProject/*.java
4. Execute by running : java finalProjects.Main
