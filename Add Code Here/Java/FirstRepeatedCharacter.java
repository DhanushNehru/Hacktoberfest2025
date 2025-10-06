import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class FirstRepeatedCharacter {
    public static void main(String[] args) {
        String input = "Java Articles are Awesome";

        // Convert the string to a stream of characters and find the first repeated character
        Optional<Character> firstRepeatedChar = input.chars()
            .mapToObj(c -> (char) c)
            .filter(c -> c != ' ') // Ignore spaces
            .collect(LinkedHashMap::new, (map, c) -> map.merge(c, 1, Integer::sum), Map::putAll)
            .entrySet()
            .stream()
            .filter(entry -> entry.getValue() > 1)
            .map(Map.Entry::getKey)
            .findFirst();

        // Display the result
        if (firstRepeatedChar.isPresent()) {
            System.out.println("First repeated character: " + firstRepeatedChar.get());
        } else {
            System.out.println("No repeated character found.");
        }
    }
}
