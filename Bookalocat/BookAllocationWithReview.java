import java.util.*;

class Book {
    int id;
    String title;
    int pages;
    List<String> reviews = new ArrayList<>();

    Book(int id, String title, int pages) {
        this.id = id;
        this.title = title;
        this.pages = pages;
    }

    void addReview(String review) {
        reviews.add(review);
    }

    void showReviews() {
        if (reviews.isEmpty()) {
            System.out.println("No reviews yet for " + title);
        } else {
            System.out.println("Reviews for " + title + ":");
            for (String r : reviews) {
                System.out.println(" - " + r);
            }
        }
    }
}

public class BookAllocationWithReview {

    // ✅ Book Allocation Problem (your earlier logic)
    private static boolean isFeasible(int[] arr, int n, int m, int mid) {
        int studentsRequired = 1;
        int currentSum = 0;

        for (int i = 0; i < n; i++) {
            if (arr[i] > mid) return false;

            if (currentSum + arr[i] > mid) {
                studentsRequired++;
                currentSum = arr[i];

                if (studentsRequired > m) return false;
            } else {
                currentSum += arr[i];
            }
        }
        return true;
    }

    public static int findPages(int[] arr, int n, int m) {
        if (m > n) return -1;

        int low = 0, high = 0;
        for (int pages : arr) {
            low = Math.max(low, pages);
            high += pages;
        }

        int result = high;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (isFeasible(arr, n, m, mid)) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 📘 Book Data
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Algorithms", 450));
        books.add(new Book(2, "Data Structures", 300));
        books.add(new Book(3, "Operating Systems", 500));
        books.add(new Book(4, "Computer Networks", 350));

        int[] arr = {450, 300, 500, 350};
        int n = arr.length;
        int m = 2;

        System.out.println("Minimum pages allocation for " + m + " students: "
                           + findPages(arr, n, m));

        // 📖 Review System
        while (true) {
            System.out.println("\n--- Book Review System ---");
            System.out.println("1. Add Review");
            System.out.println("2. Show Reviews");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            if (choice == 1) {
                System.out.println("Available books:");
                for (Book b : books) {
                    System.out.println(b.id + ". " + b.title);
                }
                System.out.print("Enter book id to review: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Write your review: ");
                String review = sc.nextLine();

                books.get(id - 1).addReview(review);
                System.out.println("✅ Review added!");
            } else if (choice == 2) {
                System.out.print("Enter book id to see reviews: ");
                int id = sc.nextInt();
                books.get(id - 1).showReviews();
            } else {
                System.out.println("Exiting...");
                break;
            }
        }

        sc.close();
    }
}
