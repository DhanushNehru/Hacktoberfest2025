// =========================================
// 🔹 Title: Simple Library Management System
// 🔹 Description: Single-file Java version
// =========================================

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// =========================================
// 🔹 Class: Book
// =========================================
class Book {
    int id;
    String title;
    String author;
    int year;
    int quantity;

    Book(int id, String title, String author, int year, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.quantity = quantity;
    }
}

// =========================================
// 🔹 Class: LibraryManagementSystem (Main Class)
// ====================
