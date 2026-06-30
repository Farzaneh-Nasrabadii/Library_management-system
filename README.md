# 📚 Desktop Library Management System

A robust, enterprise-grade Desktop Library Management Application built with Java Swing (GUI) and PostgreSQL. This system implements professional software engineering principles, including secure multi-window navigation, dynamic data rendering via JTable, and strict ACID Transaction Management for inventory tracking.

---

## 🚀 Key Features

* Secure Authentication: User login system connecting to database credentials.
* Interactive Dashboard: A professional 3x2 grid layout Admin Panel for librarians to manage daily operations seamlessly.
* Dynamic Data Views: Live database-to-GUI synchronization using custom DefaultTableModel for both Books and Members.
* ACID Transactions: Automated stock management. Loaning a book decreases available_copies by 1, and returning it increases it back by 1 safely within SQL transactions (`conn.setAutoCommit(false)`) with full rollback support on failures.

---

## 🏗️ Architecture & Design

The project follows a clean segregation of concerns loosely inspired by the MVC (Model-View-Controller) pattern:
* `view/`: Contains pure GUI layouts (`JFrame`, JPanel, JTable`) powered by Java Swing (e.g., `AdminDashboard, `ViewMembersView`).
* `model/`: Represents core entity structures (e.g., Borrow, Book, `Member`).
* `repository/`: Handles pure JDBC queries, connection lifecycles, and database transaction rollbacks.

---

## 🛠️ Tech Stack & Prerequisites

* Language: Java (JDK 17 or higher)
* GUI Framework: Java Swing / AWT
* Database: PostgreSQL 15+
* Driver: JDBC PostgreSQL Driver (JAR)

---

## 🗄️ Database Schema Quick Look

Here is the exact database structure used in this project:
-- 1. Books Table
CREATE TABLE books (
    book_id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    isbn VARCHAR(50),
    available_copies INT DEFAULT 1
);

-- 2. Members Table
CREATE TABLE members (
    member_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone VARCHAR(15),
    join_date DATE DEFAULT CURRENT_DATE
);

-- 3. Borrows Table (With Foreign Keys)
CREATE TABLE borrows (
    borrow_id SERIAL PRIMARY KEY,
    member_id INT REFERENCES members(member_id),
    book_id INT REFERENCES books(book_id),
    borrow_date DATE DEFAULT CURRENT_DATE,
    return_date DATE,
    status VARCHAR(50) DEFAULT 'BORROWED'
);

## 🏁 How to Run the Project
Follow these steps to set up and run the application on your local machine (Ubuntu/Linux or any OS):
### 1. Clone the Repositorygit clone git@github.com:Farzaneh-Nasrabadii/Library-Management-System.git
cd Library-Management-System

### 2. Database Setup
 1. Open pgAdmin or PostgreSQL CLI.
 2. Create a new database named library_db.
 3. Execute the SQL schema queries provided in the Database Schema section above to create the tables.
 4. Insert at least one admin credential or sample data if needed.
### 3. Configure Connection
Open src/repository/DatabaseConnection.java and update your PostgreSQL credentials:private static final String URL = "jdbc:postgresql://localhost:5432/library_db";
private static final String USER = "your_postgres_username";
private static final String PASSWORD = "your_postgres_password";

### 4. Compile and Run via IDE (Recommended)
 1. Open the project folder in IntelliJ IDEA, Eclipse, or VS Code.
 2. Make sure the PostgreSQL JDBC Driver (.jar) is added to your project's libraries/dependencies.
 3. Right-click on Main.java (or your initial login view window file) and select Run.
### 5. Compile and Run via Terminal (Ubuntu)
If you prefer running it directly from the terminal, navigate to the project root and run:
```bash
# Compile all java files and include the JDBC driver in the classpath
javac -cp .:postgresql*.jar -d bin src/**/*.java

# Run the application
java -cp bin:postgresql*.jar view.LoginView
