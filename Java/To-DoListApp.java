// =====================================
// 🔹 Title: Simple To-Do List Application
// =====================================

import java.util.*;

public class ToDoList {
    private static final List<String> tasks = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== TO-DO LIST =====");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Remove Task");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> addTask();
                case 2 -> viewTasks();
                case 3 -> removeTask();
                case 0 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    private static void addTask() {
        sc.nextLine();
        System.out.print("Enter new task: ");
        String task = sc.nextLine();
        tasks.add(task);
        System.out.println("✅ Task added!");
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet.");
            return;
        }
        System.out.println("\n📋 Your Tasks:");
        for (int i = 0; i < tasks.size(); i++)
            System.out.println((i + 1) + ". " + tasks.get(i));
    }

    private static void removeTask() {
        viewTasks();
        System.out.print("Enter task number to remove: ");
        int index = sc.nextInt() - 1;
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            System.out.println("✅ Task removed!");
        } else {
            System.out.println("❌ Invalid number!");
        }
    }
}
