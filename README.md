# Employee Management System

A **Java-based Employee Management System** that allows you to manage employee records efficiently through a simple graphical user interface (GUI). This project demonstrates how to integrate Java Swing with MySQL for full CRUD (Create, Read, Update, Delete) operations.

---

## Features

- **Java Swing GUI:** User-friendly interface to add, view, update, and delete employee data.
- **MySQL Integration:** Stores employee details securely in a MySQL database.
- **CRUD Operations:**  
  - **Create:** Add new employee records.  
  - **Read:** Display all employees in a table view.  
  - **Update:** Modify existing employee information.  
  - **Delete:** Remove employee records.
- **DAO Pattern:** Clean separation of database logic using Data Access Object design pattern.
- **Input Validation:** Ensures only valid data is entered (e.g., numeric salary, non-empty fields).

---

## Technologies Used

- Java SE (Swing for GUI)
- MySQL (Database)
- JDBC (Java Database Connectivity)
- MySQL Connector/J (JDBC driver)

---

## Getting Started

### Prerequisites

- Java JDK installed (version 8 or higher recommended)
- MySQL Server installed and running
- MySQL Connector/J (JDBC driver)
- Basic knowledge of Java and SQL

### Setup Instructions

1. **Clone the repository:**

   ```bash
   git clone https://github.com/FlamerFx/Employee_Management.git
   cd Employee_Management

2. **Create the MySQL database and table:**

   ```sql
   CREATE DATABASE employee_db;
   USE employee_db;

   CREATE TABLE employee (
       id INT PRIMARY KEY AUTO_INCREMENT,
       name VARCHAR(100),
       position VARCHAR(100),
       salary INT
   );

3. **Configure the database connection:**

   - Open `src/EmployeeDAO.java`.
   - Update the `DB_URL`, `USER`, and `PASS` variables to match your MySQL credentials and database name. For example:

   ```java
   private static final String DB_URL = "jdbc:mysql://localhost:3306/employee_db?useSSL=false";
   private static final String USER = "your_mysql_username";
   private static final String PASS = "your_mysql_password";

4. **Compile the Java files:**

   - Open your terminal or command prompt in the project root folder.
   - Run the following command to compile all Java files:

   ```bash
   javac -cp "lib/mysql-connector-j-9.3.0.jar" src/*.java

5. **Run the application:**

   - Run the following command in your terminal from the project root directory:

   ```bash
   java -cp "lib/mysql-connector-j-9.3.0.jar;src" EmployeeManagementSystem

## Usage
Add Employee: Enter employee details (Name, Position, Salary) and click Add.

View Employees: The table displays all employees in the database.

Update Employee: Select an employee from the table, edit the details, and click Update.

Delete Employee: Select an employee and click Delete.

## Troubleshooting
No suitable driver found: Make sure the MySQL Connector/J .jar is correctly included in your classpath.

Database connection errors: Verify your database URL, username, and password.

Port or server issues: Ensure MySQL server is running on the default port (3306) or update the URL accordingly.

This project is licensed under the MIT License.

## Acknowledgments
Java Swing tutorials for GUI design.

MySQL official documentation for database management.

JDBC documentation for database connectivity.



### Note:
  On Linux or macOS, replace the semicolon (;) with a colon (:) in the classpath like this:
```bash    
java -cp "lib/mysql-connector-j-9.3.0.jar:src" EmployeeManagementSystem




