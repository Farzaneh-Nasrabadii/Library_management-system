import controller.BookController;
import controller.MemberController;
import controller.BorrowController;
import java.util.Scanner;

public class Main {
    private static BookController bookController = new BookController();
    private static MemberController memberController = new MemberController();
    private static BorrowController borrowController = new BorrowController();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("📚 Welcome to the Advanced Library System 📚");
        System.out.println("=========================================");

        while (true) {
            System.out.println("\n--- MAIN LOGIN SCREEN ---");
            System.out.println("1. Login as Librarian (Admin)");
            System.out.println("2. Access as Library Member");
            System.out.println("3. Exit System");
            System.out.print("👉 Choose your role (1-3): ");

            String roleChoice = scanner.nextLine();

            switch (roleChoice) {
                case "1":
                    System.out.print("🔑 Enter Admin Password: ");
                    String password = scanner.nextLine();
                    if (password.equals("admin123")) {
                        System.out.println("✅ Access Granted! Welcome Admin.");
                        showAdminMenu();
                    } else {
                        System.out.println("❌ Incorrect Password! Access Denied.");
                    }
                    break;

                case "2":
                    System.out.println("✅ Welcome to the Member Panel.");
                    showMemberMenu();
                    break;

                case "3":
                    System.out.println("\nThank you for using the Library System. Goodbye! 👋");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("⚠️ Invalid choice! Please enter 1, 2, or 3.");
            }
        }
    }

    private static void showAdminMenu() {
        while (true) {
            System.out.println("\n👑 --- LIBRARIAN ADMIN PANEL ---");
            System.out.println("1. Register a New Book");
            System.out.println("2. Register a New Member");
            System.out.println("3. Issue / Borrow a Book");
            System.out.println("4. Return a Borrowed Book");
            System.out.println("5. View All Books (Report)");
            System.out.println("6. View All Members (Report)");
            System.out.println("7. Back to Login Screen");
            System.out.print("👉 Select an action (1-7): ");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        System.out.println("\n[Adding a New Book]");
                        System.out.print("Enter Title: "); String title = scanner.nextLine();
                        System.out.print("Enter Author: "); String author = scanner.nextLine();
                        System.out.print("Enter ISBN: "); String isbn = scanner.nextLine();
                        System.out.print("Enter Total Copies: ");
                        int copies = Integer.parseInt(scanner.nextLine()); // Safe from crashing via catch
                        bookController.registerNewBook(title, author, isbn, copies);
                        break;

                    case "2":
                        System.out.println("\n[Registering a New Member]");
                        System.out.print("Enter First Name: "); String fName = scanner.nextLine();
                        System.out.print("Enter Last Name: "); String lName = scanner.nextLine();
                        System.out.print("Enter Phone Number: "); String phone = scanner.nextLine();
                        memberController.registerNewMember(fName, lName, phone);
                        break;

                    case "3":
                        System.out.println("\n[Issuing a Book]");
                        System.out.print("Enter Member ID: "); int memberId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Book ID: "); int bookId = Integer.parseInt(scanner.nextLine());
                        borrowController.borrowBook(memberId, bookId);
                        break;

                    case "4":
                        System.out.println("\n[Returning a Book]");
                        System.out.print("Enter Member ID: "); int mId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Book ID: "); int bId = Integer.parseInt(scanner.nextLine());
                        borrowController.returnBook(mId, bId);
                        break;

                    case "5":
                        bookController.listAllBooks();
                        break;

                    case "6":
                        memberController.listAllMembers();
                        break;

                    case "7":
                        System.out.println("Logging out from Admin Panel...");
                        return;

                    default:
                        System.out.println("⚠️ Invalid option! Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Input Error: Please enter numbers only for IDs and copies!");
            }
            System.out.println("\n-----------------------------------------");
        }
    }

    private static void showMemberMenu() {
        while (true) {
            System.out.println("\n👤 --- MEMBER ACCESS PANEL ---");
            System.out.println("1. Search for a Book (by Title/Author/ISBN)");
            System.out.println("2. Back to Login Screen");
            System.out.print("👉 Select an action (1-2): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("\n[Searching for a Book]");
                    System.out.print("Enter keyword: ");
                    String keyword = scanner.nextLine();
                    bookController.findBooks(keyword);
                    break;

                case "2":
                    System.out.println("Returning to Login Screen...");
                    return;

                default:
                    System.out.println("⚠️ Invalid option! Try again.");
            }
            System.out.println("\n-----------------------------------------");
        }
    }
}