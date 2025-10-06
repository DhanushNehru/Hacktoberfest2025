package Java;

import java.util.HashMap;
import java.util.Map;

public class AnagramChecker {
    public static boolean isAnagram(String word1, String word2){
        //removing all non-alpha characters
        word1 = word1.replaceAll("[^a-zA-Z]", "").toLowerCase();
        word2 = word2.replaceAll("[^a-zA-Z]", "").toLowerCase();

        if(word1.length() != word2.length()) return false;

        Map<Character, Integer> countingChar = new HashMap<>();
        for(char c : word1.toCharArray()){
            countingChar.put(c, countingChar.getOrDefault(c, 0) + 1);
        }

        for(char c : word2.toCharArray()){
            if(!countingChar.containsKey(c)) return false;

            countingChar.put(c, countingChar.get(c) - 1);
            if(countingChar.get(c) == 0){
                countingChar.remove(c);
            }
        }
        return countingChar.isEmpty();
    }

    public static void main(String[] args){
        System.out.println(isAnagram("evil", "vile"));
        System.out.println(isAnagram("angel", "glean"));
        System.out.println(isAnagram("listen", "hearing"));
        System.out.println(isAnagram("school master", "the classroom"));
    }
}
