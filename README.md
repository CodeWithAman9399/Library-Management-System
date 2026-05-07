# 📚 Library Management System

A desktop-based **Library Management System** developed using **Java Swing** and **MySQL**.  
This project helps manage library operations like adding books, viewing books, issuing books, returning books, and managing users through a simple graphical user interface.

---

## 🚀 Features

### 👨‍💼 Admin Features
- Add new books
- View all books
- Create admin accounts
- Manage library records

### 👤 User Features
- User registration and login
- View available books
- Issue books
- Return books
- Track issued books

### 🔐 Authentication
- Role-based login system
- Separate dashboards for Admin and Users

---

## 🛠️ Technologies Used

- Java
- Java Swing
- JDBC
- MySQL
- OOP Concepts

---

## 📂 Project Structure

```bash
LibraryManagementSystem/
│── Main.java
│── AdminDashboard.java
│── UserDashboard.java
│── ManageLibrary.java
│── AddBookForm.java
│── ViewBooks.java
│── IssueBookForm.java
│── ReturnBookForm.java
│── CreateAdminForm.java
│── RegisterForm.java
│── AuthService.java
│── Book.java
│── BookDAO.java
│── IssuedBook.java
│── User.java
│── UserDAO.java
│── DBConnection.java
│── README.md
```

---

## ⚡ How to Run

### 1️⃣ Clone Repository

```bash
git clone https://github.com/your-username/LibraryManagementSystem.git
```

### 2️⃣ Open Project in IDE

Use any Java IDE:
- IntelliJ IDEA
- Eclipse
- NetBeans

### 3️⃣ Setup MySQL Database

Create database:

```sql
CREATE DATABASE library_db;
```

### 4️⃣ Configure Database Connection

Update database credentials in `DBConnection.java`

```java
private static final String URL = "jdbc:mysql://localhost:3306/library_db";
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

### 5️⃣ Add MySQL JDBC Driver

Add MySQL Connector/J library to your project.

### 6️⃣ Run the Project

```bash
javac Main.java
java Main
```

---

## 🖥️ Screens Included

- Login Form
- Registration Form
- Admin Dashboard
- User Dashboard
- Add Book Form
- View Books
- Issue Book Form
- Return Book Form

---

## 📚 Concepts Used

- Java Swing GUI
- Event Handling
- JDBC Connectivity
- MySQL Database
- Object-Oriented Programming
- Exception Handling

---

## 🔮 Future Improvements

- Password encryption
- Fine management system
- Book search feature
- Dark mode UI
- Email notifications
- Report generation

---

## 👨‍💻 Author

**Aman**

---

## 📄 License

This project is open source and available under the MIT License.

---

⭐ If you like this project, give it a star on GitHub!
