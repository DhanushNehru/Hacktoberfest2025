public class counting_sort {

public static void sort(int n[]){
    int largest = Integer.MIN_VALUE;
    for(int i=0; i<n.length;i++){
        largest=Math.max(largest, n[i]);

    }
    int count[] = new int[largest+1];
    for(int j=0;j<n.length;j++){
        count[n[j]]++;
    }
    int j=0;
    for(int i=0;i<count.length;i++){
        while(count[i]>0){
            n[j]=i;
            j++;
            count[i]--;
        }
    }
}

    public static void printarr(int n[]) {
        for (int i = 0; i < n.length; i++) {
            System.out.print(n[i] + " "); // Added space for better readability
        }
        System.out.println("");
    }
    public static void main(String[] args) {
        int n[] = {1, 5, 2, 6, 3, 4, 8};
        sort(n);
        printarr(n);
    }
}


