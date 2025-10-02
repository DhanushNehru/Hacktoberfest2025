class Methods {
    public void CompareToMethod() {
        /*
         * this method is used to compare two strings if two strings are same then it
         * will return 0....., if first string is
         * lexographically greater than second string then it will return positive
         * integer.........,
         * else if first string is lexicographically shorter than second string then it
         * will return
         * negative integer..
         * Note - it will return positive or negative integer according to Position of
         * that char in
         * alphabet table
         */
        System.out.println("Compare Method----");
        String str = "vansh";
        String skr = "vansh";
        String smr = "zansh";
        String slr = "bansh";
        int res = str.compareTo(skr);// this will return 0 'cause both strings are same......
        int res1 = str.compareTo(smr);// this will return -4 'cause 'vansh' is lexicographically smaller than 'zansh'
                                      // and z is placed after 4 position than v
        int res2 = str.compareTo(slr);// this will return 20 'cause 'vansh' is lexicographically greater than 'bansh'
        // and b is placed before 20 position than v
        System.out.println(res + " - Means strings are equal");
        System.out.println(res1 + " - Means first one is lexicographically smaller than second string");
        System.out.println(res2 + " - Means first one is lexicographically greater than second string");
    }

    public void ContainsMethod() {
        // this method returns true or false according to character-set is present in
        // the string or not
        // Note - character-set must be in double coute "charset";
        System.out.println("Contains Method----");
        String str = "vansh soni";
        boolean a = str.contains("sh");
        boolean b = str.contains("kal");
        System.out.println(a);
        System.out.println(b);
    }

    public void StartsWithMethod() {
        System.out.println("StartsWith Method----");
        String str = "vansh soni";
        String srt = "TOMATO";
        boolean a = str.startsWith("va");
        boolean b = str.endsWith("oni");
        System.out.println(a);
        System.out.println(b);
        System.out.println("ToUpperCase Method----");
        System.out.println(str.toUpperCase());
        System.out.println("ToLowerCase Method----");
        System.out.println(srt.toLowerCase());
        System.out.println("TOcharArray Method----");
        char[] c = str.toCharArray();
        System.out.println(c);
        System.out.println(c[3]);
    }
}

public class Metho {
    public static void main(String[] args) {
        Methods m1 = new Methods();
        m1.CompareToMethod();
        m1.ContainsMethod();
        m1.StartsWithMethod();
    }
}