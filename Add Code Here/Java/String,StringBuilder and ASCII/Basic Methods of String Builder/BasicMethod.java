public class BasicMethod {
    public static void main(String[] args) {
        /*
         * Append means to add at last and insert means we can add at any particular
         * index
         */
        StringBuilder str = new StringBuilder();
        str.append("Hello");
        System.out.println(str);
        str.append(" Vansh");
        System.out.println(str);
        System.out.println(str.indexOf("va"));// -1 'cause va is not present in the string.............
        System.out.println(str.indexOf(" "));// 5
        System.out.println(str.indexOf("V"));// 6
        str.setCharAt(0, 'B');// Bello Vansh
        System.out.println(str);
        str.insert(0, "Hello ");// Hello Bello Vansh
        System.out.println(str);
        str.append(4.89f);// f represents float
        System.out.println(str);
        str.delete(0, 6);// Bello Vansh4.89
        System.out.println(str);
        str.insert(1, 9);
        System.out.println(str);// B9ello Vansh4.89
        str.insert(5, 'K');
        System.out.println(str);// B9ellKo Vansh4.89
        str.insert(9, "I am a disco dancer");
        System.out.println(str);
        StringBuilder str1 = new StringBuilder("Physics");
        str1.reverse();
        System.out.println(str1);
    }
}