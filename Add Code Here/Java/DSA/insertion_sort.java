public class insertion_sort {

    public static void sort(int n[]){
        for(int i=1;i<n.length-1;i++){
            int current =n[i];
            int previous = i-1;
            while(previous>=0 && n[previous]>current){
                n[previous+1]=n[previous];
                previous--;
            }
            n[previous+1]=current;
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
