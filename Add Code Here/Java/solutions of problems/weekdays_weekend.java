import java.util.Scanner;

public class weekdays_weekend {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the day no: ");

        int dayNum = sc.nextInt();

        switch(dayNum){
            case 1,2,3,4,5: {System.out.println("Weekdays");break;}
            case 6,7: {System.out.println("Weekend");break;}
            default: {System.out.println("invalid input");}
        }
    }
}
