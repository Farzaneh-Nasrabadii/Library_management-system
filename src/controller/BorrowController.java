package controller;

import model.Borrow;
import repository.BorrowRepository;
import java.util.Date;

public class BorrowController {
    private BorrowRepository borrowRepository;

    public BorrowController() {
        this.borrowRepository = new BorrowRepository();
    }

    // Business Logic: Process a new book borrowing transaction
    public void borrowBook(int memberId, int bookId) {
        // Validation: Ensure IDs are valid numbers
        if (memberId <= 0 || bookId <= 0) {
            System.out.println("⚠️ Validation Error: Invalid Member ID or Book ID!");
            return;
        }

        // Create a new borrow record with current date and "BORROWED" status
        Borrow borrow = new Borrow(0, memberId, bookId, new Date(), null, "BORROWED");

        // Save the transaction record to the database
        borrowRepository.javaBorrowBook(borrow);
    }
}