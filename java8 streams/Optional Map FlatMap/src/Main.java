import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);

        List<List<String>> nestedWords = Arrays.asList(
                Arrays.asList("Java", "Stream"),
                Arrays.asList("API", "Lambda"),
                Arrays.asList("FlatMap", "Map")
        );

        // Using flatMap to make the nested lists become one normal list.
        List<String> flattenedWords = nestedWords.stream()
                .flatMap(oneList -> oneList.stream())
                .collect(Collectors.toList());
        System.out.println("Flattened words: " + flattenedWords);

        // Taking every character from the words, then keeping each character one time only.
        List<Character> uniqueCharacters = flattenedWords.stream()
                .flatMapToInt(word -> word.chars())
                .distinct()
                .mapToObj(characterCode -> (char) characterCode)
                .collect(Collectors.toList());
        System.out.println("Unique characters: " + uniqueCharacters);

        List<Optional<String>> opts = Arrays.asList(Optional.of("Ali"), Optional.empty(), Optional.of("Sara"), Optional.empty());

        // Keeping only the Optional values that actually have data.
        List<String> presentValues = opts.stream()
                .filter(optionalValue -> optionalValue.isPresent())
                .map(optionalValue -> optionalValue.get())
                .collect(Collectors.toList());
        System.out.println("Non-empty optional values: " + presentValues);

        // Converting every valid name to its length.
        List<Integer> nameLengths = names.stream()
                .filter(name -> name != null)
                .map(name -> name.length())
                .collect(Collectors.toList());
        System.out.println("Name lengths: " + nameLengths);

        // Keeping names that start with A, then making them uppercase.
        List<String> uppercasedWordsStartingWithA = names.stream()
                .filter(name -> name != null)
                .filter(name -> name.startsWith("A"))
                .map(name -> name.toUpperCase())
                .collect(Collectors.toList());
        System.out.println("Uppercased words starting with A: " + uppercasedWordsStartingWithA);
    }
}
