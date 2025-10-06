
import java.util.*;
 
public class Main {
    public static int findPlatformOptimized(int[] arr, int[] dep, int n)
    {
        int count = 0, maxPlatforms = 0;
        int maxDepartureTime = dep[0];
        for (int i = 1; i < n; i++) {
            maxDepartureTime = Math.max(maxDepartureTime, dep[i]);
        }
 
        List<Integer> v = new ArrayList<>(maxDepartureTime + 2);
        for (int i = 0; i < maxDepartureTime + 2; i++) {
            v.add(0);
        }

        for (int i = 0; i < n; i++) {
            v.set(arr[i], v.get(arr[i]) + 1);
            v.set(dep[i] + 1, v.get(dep[i] + 1) - 1);
        }
 
        for (int i = 0; i <= maxDepartureTime + 1; i++) {
            count += v.get(i);
            maxPlatforms = Math.max(maxPlatforms, count);
        }
 
        return maxPlatforms;
    }
 
    // Driver Code
    public static void main(String[] args)
    {
        int[] arr = { 100, 300, 600 };
        int[] dep = { 900, 400, 500 };
        int n = arr.length;
        System.out.println(findPlatformOptimized(arr, dep, n));
    }
}
