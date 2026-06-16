package controller;

import model.Book;
import repository.BookRepository;

public class BookController {
    // Connect the controller to the repository layer
    private BookRepository bookRepository;

    public BookController() {
        this.bookRepository = new BookRepository();
    }

    // Business Logic: Validate book data before saving it to the database
    public void registerNewBook(String title, String author, String isbn, int totalCopies) {
        // Simple validation: Ensure title and ISBN are not empty
        if (title == null || title.trim().isEmpty()) {
            System.out.println("⚠️ Validation Error: Book title cannot be empty!");
            return;
        }
        if (isbn == null || isbn.trim().isEmpty()) {
            System.out.println("⚠️ Validation Error: ISBN cannot be empty!");
            return;
        }

        // If validation passes, create a new Book model object
        // availableCopies is initially equal to totalCopies
        Book book = new Book(0, title, author, isbn, totalCopies, totalCopies);

        // Pass the object to the repository layer to save it
        bookRepository.addBook(book);
    }
}