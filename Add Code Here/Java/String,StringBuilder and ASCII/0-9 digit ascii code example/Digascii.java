public class Digascii {
    public static void main(String[] args) {
        char ch = '4';// the ascii code of 4 is 52;
        int intValueOfChar = ch - '0';// the ascii code of 0 is 48;
        // So here in the background for the code ch - '0' ...... actually 52 - 48
        // process is happening those ans is = 4;
        System.out.println(intValueOfChar);// So the value of the intValueOfChar will be 4;
    }
}
