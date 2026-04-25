import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);

        // Counting the names that are not null and have more than 5 characters.
        long namesLongerThanFive = names.stream()
                .filter(name -> name != null)
                .filter(name -> name.length() > 5)
                .count();
        System.out.println("Count of names longer than 5 chars: " + namesLongerThanFive);

        // Finding the first number that is greater than 7.
        int firstNumberGreaterThanSeven = numbers.stream()
                .filter(number -> number > 7)
                .findFirst()
                .orElse(-1);
        System.out.println("First number greater than 7: " + firstNumberGreaterThanSeven);

        // Checking if there is at least one number divisible by 5.
        boolean hasNumberDivisibleByFive = numbers.stream()
                .anyMatch(number -> number % 5 == 0);
        System.out.println("Any number divisible by 5: " + hasNumberDivisibleByFive);

        // Collecting the numbers into a Set, so repeated values will not appear twice.
        Set<Integer> numbersAsSet = numbers.stream()
                .collect(Collectors.toSet());
        System.out.println("Numbers collected as set: " + numbersAsSet);

        // Skipping the first 3 numbers and collecting the rest.
        List<Integer> numbersAfterSkippingThree = numbers.stream()
                .skip(3)
                .collect(Collectors.toList());
        System.out.println("Numbers after skipping first 3: " + numbersAfterSkippingThree);
    }
}
