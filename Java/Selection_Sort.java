import java.util.Scanner;

class Selection_Sort {

    void initialize(int arr[]) {
        System.out.println("Enter array elements:");
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < arr.length; i++)
            arr[i] = in.nextInt();
        in.close();
    }

    void display(int arr[]) {
        System.out.println("The array elements are:");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    void sort(int arr[]) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {  
                if (arr[j] < arr[min])
                    min = j;
            }
            arr[i] = arr[i] + arr[min] - (arr[min] = arr[i]);
        }
    }

    public static void main(String args[]) {
        int[] arr = new int[5];
        Selection_Sort sorter = new Selection_Sort();  
        sorter.initialize(arr);
        sorter.display(arr);
        sorter.sort(arr);
        sorter.display(arr);
    }
}
