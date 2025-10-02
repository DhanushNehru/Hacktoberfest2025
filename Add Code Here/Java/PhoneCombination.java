import java.util.*;

public class PhoneCombination {
    public static List<String> GiveCombination(String[] arr, String digits, StringBuilder ans, ArrayList<String> list) {
        if (digits.length() == 0) {
            list.add(ans.toString());// converting Stringbuilder to String............
            return list;
        }
        int idx = digits.charAt(0) - '0';
        String str = arr[idx];
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            GiveCombination(arr, digits.substring(1), ans.append(ch), list);// recursive work.......
            ans.deleteCharAt(ans.length() - 1);// backtracking ......deleting the current character from the ans....
        }
        return list;
    }

    public static List<String> letterCombinations(String digits) {
        if (digits.equals("")) {
            return new ArrayList<>();
        }
        ArrayList<String> list = new ArrayList<>();
        StringBuilder ans = new StringBuilder();
        String[] arr = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };// we can also use
                                                                                            // hashmap...........instead
                                                                                            // of array.....
        return GiveCombination(arr, digits, ans, list);
    }

    public static void main(String[] args) {
        String digits = "23";
        ArrayList<String> ans = (ArrayList<String>) letterCombinations(digits);// we can also list to
                                                                               // ArrayList<>...........
        System.out.println(ans);
    }
}
