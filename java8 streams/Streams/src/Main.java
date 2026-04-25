import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);
        String specificLetter = "A";

        // Filtering the list and keeping only the even numbers.
        List<Integer> evenNumbers = numbers.stream()
                .filter(number -> number % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Even numbers: " + evenNumbers);

        // Checking the names that start with the chosen letter, and ignoring null first.
        List<String> namesStartingWithLetter = names.stream()
                .filter(name -> name != null)
                .filter(name -> name.startsWith(specificLetter))
                .collect(Collectors.toList());
        System.out.println("Names starting with " + specificLetter + ": " + namesStartingWithLetter);

        // Converting every non-null name to uppercase.
        List<String> uppercasedNames = names.stream()
                .filter(name -> name != null)
                .map(name -> name.toUpperCase())
                .collect(Collectors.toList());
        System.out.println("Uppercased names: " + uppercasedNames);

        // Sorting the numbers in descending order.
        List<Integer> numbersDescending = numbers.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println("Numbers in descending order: " + numbersDescending);

        // Removing the repeated numbers by using distinct.
        List<Integer> distinctNumbers = numbers.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Distinct numbers: " + distinctNumbers);
    }
}
