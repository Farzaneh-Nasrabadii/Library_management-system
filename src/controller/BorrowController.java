package controller;

import model.Borrow;
import repository.BorrowRepository;
import java.util.Date;

public class BorrowController {
    private BorrowRepository borrowRepository;

    public BorrowController() {
        this.borrowRepository = new BorrowRepository();
    }

    // Business Logic: Process borrowing with transaction
    public void borrowBook(int memberId, int bookId) {
        if (memberId <= 0 || bookId <= 0) {
            System.out.println("⚠️ Validation Error: Invalid Member ID or Book ID!");
            return;
        }

        // Create a new borrow record with current date and "BORROWED" status
        Borrow borrow = new Borrow(0, memberId, bookId, new Date(), null, "BORROWED");

        // Pass to repository to execute the transaction
        borrowRepository.javaBorrowBook(borrow);
    }

    // Business Logic: Process returning a book with transaction
    public void returnBook(int memberId, int bookId) {
        if (memberId <= 0 || bookId <= 0) {
            System.out.println("⚠️ Validation Error: Invalid Member ID or Book ID!");
            return;
        }

        // Pass request to repository layer to update both status and available copies
        borrowRepository.returnBook(memberId, bookId);
    }
}