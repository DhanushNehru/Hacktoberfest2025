package String;

public class StringCompression{
    public static String compress(String str) {
        StringBuilder compressed = new StringBuilder();
        int count = 1;

        for (int i = 0; i < str.length(); i++) {
            if (i + 1 < str.length() && str.charAt(i) == str.charAt(i + 1)) {
                count++;
            } else {
                compressed.append(str.charAt(i)).append(count);
                count = 1;
            }
        }

        return compressed.length() < str.length() ? compressed.toString() : str;
    }

    public static void main(String[] args) {
        String str = "aaabbbcccaaa";
        System.out.println("Compressed String: " + compress(str));
    }
}
