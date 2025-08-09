
    public class Box_Stars {
        public static void main(String[] args) {
            for (int i = 1; i <= 4; i++) {          // Outer loop for the number of rows
                for (int j = 1; j <= 6; j++) {      // Inner loop for the number of columns
                    System.out.print("*");          // Use print() to stay on the same line
                }
                System.out.println();               // Move to the next line after printing one row
            }
        }
    }
    
