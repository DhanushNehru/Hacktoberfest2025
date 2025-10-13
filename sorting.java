    import java.util.Arrays;

    public class ArraySortingExample {
        public static void main(String[] args) {
            int[] numbers = {5, 2, 8, 1, 9};
            Arrays.sort(numbers); // Sorts in ascending order
            System.out.println("Sorted numbers: " + Arrays.toString(numbers));

            String[] names = {"Charlie", "Alice", "Bob"};
            Arrays.sort(names); // Sorts alphabetically
            System.out.println("Sorted names: " + Arrays.toString(names));
        }
    }
