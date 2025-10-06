
import java.util.Scanner;

public class Algorithm_for_stars{


    public static void left_triangle(int n){
        int total_lines= n ;
        int spaces = n-1;
        int stars = 1;
        int curent_lines=1;
        while(curent_lines<=total_lines){
            //print spaces
            for(int i=1;i<=spaces;i++){
                System.out.print(" ");
            }
            // print stars 
            for(int j=1; j<=stars;j++){
                System.out.print("*");
            }
            //print next line 
            System.out.println("");
            //prepare fro the next lines
            curent_lines++;
            spaces--;
            stars++;

        }
    }

    public static void number_pyrimid(int n){
        int total_lines= n ;
        int stars = 1;
        int spaces = n-1;

        int curent_lines=1;


        while(curent_lines<=total_lines){
            //print spaces
            for(int i=1;i<=spaces;i++){
                System.out.print(" ");
            }
            // print stars 
            for(int j=1; j<=stars;j++){
                System.out.print(j+" ");
            }
            //print next line 
            System.out.println("");
            //prepare fro the next lines
            curent_lines++;
            stars++; 
            spaces--;
            }}

            public static void pelindrome_diamond(int n){
                // diamond 
            //      *
            //     ***
            //    *****
            //   *******
            //    *****
            //     ***
            //      *
            // pelindrome dimond
                //       1
                //      121
                //     12321
                //    1234321
                //   123454321
                //    1234321
                //     12321
                //      121
                //       1
                int rows, i, space, star = 1;
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter Number of Rows ");
                rows = scanner.nextInt();
       
                for (i = 1; i <= rows; i++)
                {
                    for (space = 1; space <= rows - i; space++)
                    {
                        System.out.print(" ");
                    }
       
                    while (star <= (2 * i - 1))
                    {
                        System.out.print(-Math.abs(star - i) + i);
                        star++;
                    }
                    star = 1;
                    System.out.print("\n");
                }
                for (i = rows-1; i >= 1; i--)
                {
                    for (space = rows - i; space >= 1; space--)
                    {
                        System.out.print(" ");
                    }

                    while (star <= (2 * i - 1))
                    {
                        System.out.print(-Math.abs(star - i) + i);
                        star++;
                    }
                    star = 1;
                    System.out.print("\n");
                }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter numbers of lines:");
        int n = sc.nextInt() ;
        // left_triangle(n);
        // number_pyrimid(n);
        pelindrome_diamond(n);
    }
}