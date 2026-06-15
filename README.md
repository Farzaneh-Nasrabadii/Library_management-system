Library Management System

A comprehensive, desktop-based Library Management System designed to manage library operations, track member records, handle book inventories, and manage book lending processes.

This project is built using Java (OOP) and PostgreSQL, structured to demonstrate clean code, database relations, and desktop application development.


Features:

- Member Management: Add, view, and delete library members with unique Membership IDs.
- Book Inventory Control: Manage book details (Title, Author, ISBN, and stock count).
- Core Lending System: - Issue books to members and track status.
- Process book returns and automatically update inventory stock in real-time.
- Prevention mechanisms (e.g., cannot borrow a book if available copies = 0).
- Database Persistence: All data is securely stored and managed via a PostgreSQL database.
- User-Friendly GUI: Built with Java Swing for an intuitive desktop experience.


Tech Stack & Tools:

- Language: Java (JDK 17 or higher)
- GUI Framework: Java Swing
- Database: PostgreSQL
- Database Connectivity: JDBC (PostgreSQL Driver)
- Version Control: Git & GitHub


Database Architecture (ERD Summary):

The system relies on three interconnected tables:
1. Members: Stores personal details and unique member codes.
2. Books: Tracks book details, ISBN, and live stock count.
3. Borrows: Handles the relationship between Members and Books (issue dates, return dates, and status).


How to Run the Project:

Prerequisites
- Java Development Kit (JDK) 17 or higher installed.
- PostgreSQL database server running locally.
- An IDE (IntelliJ IDEA or NetBeans).

Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/Farzaneh-Nasrabadii/Library-management-System.git
