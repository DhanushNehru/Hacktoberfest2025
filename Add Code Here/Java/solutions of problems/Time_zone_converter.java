import java.util.Scanner;


public class Time_zone_converter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Day: ");
        int Day =sc.nextInt();
        System.out.print("Enter the hour: ");
        int hour =sc.nextInt();
        System.out.print("Enter the Minutes: ");
        int Minutes =sc.nextInt();

        hour +=5;
        Minutes +=30;

        if(Minutes>=60){
            hour++;
            Minutes -=60;
        }
        if(hour>=24){
            Day++;
            hour-=24;
        }
        if(Day>=31){
                    Day-=31;
        }
        System.out.print("IST:"+Day+":"+hour+":"+Minutes);
    }
}
