import controller.BookController;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Library Management System - Advanced Search Test...");

        BookController bookController = new BookController();

        // Test 1: Searching by partial title (simulating user typing a name)
        System.out.println("\n--- Testing Search by Title ---");
        bookController.findBooks("Clean");

        // Test 2: Searching by exact ISBN (simulating a barcode scanner input)
        System.out.println("\n--- Testing Search by ISBN ---");
        bookController.findBooks("978-0134685991");
    }
}