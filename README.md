# 📚 Advanced Library Management System

A robust, enterprise-structured Desktop/Console Library Management System designed to streamline library operations, track member records, handle live book inventories, and manage secure lending processes.

This project is built using Java (OOP) and PostgreSQL, strictly adhering to the MVC (Model-View-Controller) architecture and database transaction safety (ACID principles).

---

## 🏗️ Architecture & Design Patterns

The application is split into distinct layers to ensure high maintainability and loose coupling:
* Model Layer: Encapsulates core entities (`Book`, Member, `Borrow`) using strict encapsulation.
* Repository Layer (DAL): Manages raw database interactions using compiled SQL through PreparedStatement to prevent SQL Injection.
* Controller Layer: Coordinates business logic, input validation, and data routing.
* View Layer: Handles interactive console selection and user role boundaries.

---

## ✨ Key Features

* Role-Based Access Control (RBAC): Separate interfaces for Librarians (Admin) with full CRUD credentials and Members with read-only search privileges.
* Inventory Automation & Transactions: Borrowing a book automatically decreases available_copies by 1, and returning it increments it. Backed by SQL ROLLBACK mechanisms to prevent data corruption.
* Input Validation & Exception Handling: Comprehensive try-catch blocks prevent system crashes from faulty keyboard inputs (e.g., entering text instead of numeric IDs).
* Robust Search Engine: Implements case-insensitive partial matching (`ILIKE`) for quick catalog lookup.

---

## 🛠️ Tech Stack & Tools

* Language: Java (JDK 17)
* Database: PostgreSQL
* Connectivity: JDBC (PostgreSQL Driver)
* Version Control: Git & GitHub

---

## 🗄️ Database Schema (ERD Summary)

The system relies on three relational tables managed under strict Foreign Key constraints:
1. books (Unique isbn, track total_copies vs `available_copies`).
2. members (Tracks personal details and contact numbers).
3. borrows (Junction table tracking active loans, transaction dates, and status fields like BORROWED or `RETURNED`).

---

## 🚀 How to Run the Project

### Prerequisites
* Java Development Kit (JDK) 17 or higher installed.
* PostgreSQL database server running locally.

### Installation & Setup

1. Clone the repository:
git clone https://github.com/Farzaneh-Nasrabadii/Library-Management-System.git
