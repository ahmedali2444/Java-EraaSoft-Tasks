import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        List<String> words = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);

        System.out.println(nums.stream().filter(n -> n % 2 == 0).collect(Collectors.toList()));

        System.out.println(words.stream().filter(n -> n != null && n.startsWith("A")).collect(Collectors.toList()));

        System.out.println(words.stream().filter(n -> n != null && !n.isEmpty()).map(String::toUpperCase).collect(Collectors.toList()));

        System.out.println(nums.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()));

        System.out.println(nums.stream().distinct().collect(Collectors.toList()));
    }
}