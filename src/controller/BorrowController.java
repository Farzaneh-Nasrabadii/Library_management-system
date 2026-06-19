package controller;

import model.Borrow;
import repository.BorrowRepository;
import java.util.Date;

public class BorrowController {
    private BorrowRepository borrowRepository;

    public BorrowController() {
        this.borrowRepository = new BorrowRepository();
    }

    // Business Logic: Process borrowing
    public void borrowBook(int memberId, int bookId) {
        if (memberId <= 0 || bookId <= 0) {
            System.out.println("⚠️ Validation Error: Invalid Member ID or Book ID!");
            return;
        }
        Borrow borrow = new Borrow(0, memberId, bookId, new Date(), null, "BORROWED");
        borrowRepository.javaBorrowBook(borrow);
    }

    // NEW Business Logic: Process returning a book
    public void returnBook(int memberId, int bookId) {
        if (memberId <= 0 || bookId <= 0) {
            System.out.println("⚠️ Validation Error: Invalid Member ID or Book ID!");
            return;
        }
        // Pass request to repository layer
        borrowRepository.returnBook(memberId, bookId);
    }
}