import controller.MemberController;
import controller.BorrowController;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Library Management System - Final Controller Test...");

        // 1. Instantiate the controllers
        MemberController memberController = new MemberController();
        BorrowController borrowController = new BorrowController();

        // 2. Test Member Validation (Invalid Data)
        System.out.println("\n--- Testing Invalid Member Data ---");
        memberController.registerNewMember("Farzaneh", "", "123"); // Last name empty, phone too short

        // 3. Test Member Registration (Valid Data)

        System.out.println("\n--- Testing Valid Member Registration ---");
        memberController.registerNewMember("Ali", "Rezaei", "09876543210");

        // 4. Test Borrowing Process
        // NOTE: Make sure member_id 1 and book_id 1 exist in your database tablesSystem.out.println("\n--- Testing Book Borrowing Process ---");
        borrowController.borrowBook(1, 1);
    }
}