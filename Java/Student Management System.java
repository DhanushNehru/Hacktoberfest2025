// =====================================
// 🔹 Title: Student Management System
// =====================================

import java.util.*;

class Student {
    int id;
    String name;
    double grade;

    Student(int id, String name, double grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }
}

public class StudentManagementSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Student> students = new ArrayList<>();
    private static int nextId = 1;

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Delete Student");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> deleteStudent();
                case 0 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private static void addStudent() {
        scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter grade: ");
        double grade = scanner.nextDouble();
        students.add(new Student(nextId++, name, grade));
        System.out.println("✅ Student added!");
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student s : students)
            System.out.printf("[%d] %s - Grade: %.2f%n", s.id, s.name, s.grade);
    }

    private static void deleteStudent() {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        students.removeIf(s -> s.id == id);
        System.out.println("✅ Student removed if existed.");
    }
}
