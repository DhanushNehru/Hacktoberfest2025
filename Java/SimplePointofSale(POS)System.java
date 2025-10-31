// =====================================
// 🔹 Title: Simple Point of Sale System
// =====================================

import java.util.*;

class Product {
    String name;
    double price;
    Product(String name, double price) { this.name = name; this.price = price; }
}

public class SimplePOS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Product> cart = new ArrayList<>();

        System.out.println("===== SIMPLE POS SYSTEM =====");
        while (true) {
            System.out.println("1. Add Product");
            System.out.println("2. View Cart");
            System.out.println("3. Checkout");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                sc.nextLine();
                System.out.print("Enter product name: ");
                String name = sc.nextLine();
                System.out.print("Enter price: ");
                double price = sc.nextDouble();
                cart.add(new Product(name, price));
                System.out.println("✅ Added to cart!");
            } else if (choice == 2) {
                double total = 0;
                System.out.println("\n🛍️ Cart:");
                for (Product p : cart) {
                    System.out.println(p.name + " - $" + p.price);
                    total += p.price;
                }
                System.out.println("Total: $" + total);
            } else if (choice == 3) {
                double total = cart.stream().mapToDouble(p -> p.price).sum();
                System.out.println("💰 Total Amount: $" + total);
                cart.clear();
                System.out.println("Checkout complete!");
            } else if (choice == 0) break;
        }
        sc.close();
    }
}
