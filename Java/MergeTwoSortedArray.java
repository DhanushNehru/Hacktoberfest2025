package Java;

public class MergeTwoSortedArray {

    // This method merges two sorted arrays into one sorted array
    public static int[] merge(int[] arr1 , int[] arr2){
        int n = arr1.length; // Length of first array
        int m = arr2.length; // Length of second array
        int ans[]  = new int[n+m]; // Result array to store merged elements
        int i = 0 , j = 0; // Pointers for arr1 and arr2
        int k = 0; // Pointer for ans array

        // Traverse both arrays until one is fully processed
        while(i < n && j < m){

            // Compare current elements of both arrays
            // Add the smaller one to the result array
            if(arr1[i] <= arr2[j]){
                ans[k++] = arr1[i]; // Add arr1[i] to result and move i forward
                i++;
            }
            else{
                ans[k++] = arr2[j]; // Add arr2[j] to result and move j forward
                j++;
            }
        }

        // If any elements are left in arr1, add them to result
        while(i < n){
            ans[k++] = arr1[i];
            i++;
        }

        // If any elements are left in arr2, add them to result
        while(j < m){
            ans[k++] = arr2[j];
            j++;
        }

        // Return the merged sorted array
        return ans;
    }

    public static void main(String[] args){
        int arr1[] = {1,2,5,7}; // First sorted array
        int arr2[] = {4,6,8,9,12,34}; // Second sorted array
        int res[] = merge(arr1 , arr2); // Call merge method

        // Print the sorted and merged array
        for(int i = 0 ; i < res.length ; i++){
            System.out.print(res[i] + " ");
        }
    }
}