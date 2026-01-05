// =====================================
// 🔹 Title: Personal Finance Tracker
// =====================================

import java.util.*;

class Transaction {
    String type;
    double amount;
    String description;

    Transaction(String type, double amount, String description) {
        this.type = type;
        this.amount = amount;
        this.description = description;
    }
}

public class FinanceTracker {
    private static final List<Transaction> transactions = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== PERSONAL FINANCE TRACKER =====");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Summary");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> addTransaction("Income");
                case 2 -> addTransaction("Expense");
                case 3 -> viewSummary();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    private static void addTransaction(String type) {
        sc.nextLine();
        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter description: ");
        String desc = sc.nextLine();
        transactions.add(new Transaction(type, amount, desc));
        System.out.println("✅ Transaction recorded!");
    }

    private static void viewSummary() {
        double income = 0, expense = 0;
        for (Transaction t : transactions) {
            if (t.type.equals("Income")) income += t.amount;
            else expense += t.amount;
        }
        System.out.println("\n💵 Income: $" + income);
        System.out.println("💸 Expense: $" + expense);
        System.out.println("📊 Balance: $" + (income - expense));
    }
}
