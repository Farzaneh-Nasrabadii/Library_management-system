package controller;

import model.Book;
import repository.BookRepository;
import java.util.List;

public class BookController {
    private BookRepository bookRepository;

    public BookController() {
        this.bookRepository = new BookRepository();
    }

    public void registerNewBook(String title, String author, String isbn, int totalCopies) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("⚠️ Validation Error: Book title cannot be empty!");
            return;
        }
        if (isbn == null || isbn.trim().isEmpty()) {
            System.out.println("⚠️ Validation Error: ISBN cannot be empty!");
            return;
        }
        if (totalCopies < 0) {
            System.out.println("⚠️ Validation Error: Total copies cannot be negative!");
            return;
        }
        Book book = new Book(0, title, author, isbn, totalCopies, totalCopies);
        bookRepository.addBook(book);
    }

    public void findBooks(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("⚠️ Please enter a keyword to search!");
            return;
        }
        List<Book> results = bookRepository.searchBooks(keyword);
        if (results.isEmpty()) {
            System.out.println("🔍 No books found matching: " + keyword);
        } else {
            System.out.println("🔍 Search Results for '" + keyword + "':");
            for (Book b : results) {
                System.out.println(" -> ID [" + b.getBookId() + "] " + b.getTitle() + " by " + b.getAuthor() + " | Available: " + b.getAvailableCopies() + "/" + b.getTotalCopies());
            }
        }
    }

    // NEW METHOD: Display inventory report
    public void listAllBooks() {
        List<Book> books = bookRepository.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("📭 The library database has no books registered yet.");
        } else {
            System.out.println("\n📚 --- COMPLETE BOOK LIST ---");
            for (Book b : books) {
                System.out.println(" -> ID: " + b.getBookId() + " | Title: " + b.getTitle() + " | Author: " + b.getAuthor() + " | Available: " + b.getAvailableCopies() + "/" + b.getTotalCopies());
            }
        }
    }
}