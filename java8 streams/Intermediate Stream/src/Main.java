import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        List<String> words = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);

        System.out.println(words.stream().filter(n -> n != null && n.length() > 5).count());

        System.out.println(nums.stream().filter(n -> n > 7).findFirst().orElse(-1));

        System.out.println(nums.stream().anyMatch(n -> n % 5 == 0));

        System.out.println(nums.stream().collect(Collectors.toSet()));

        System.out.println(nums.stream().skip(3).collect(Collectors.toList()));
    }
}