import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);

        System.out.println(nums.stream().reduce(0, Integer::sum));

        System.out.println(nums.stream().max(Integer::compareTo).get());
        System.out.println(nums.stream().min(Integer::compareTo).get());

        System.out.println(nums.stream().mapToInt(Integer::intValue).average().getAsDouble());

        System.out.println(nums.stream().reduce(1, (a, b) -> a * b));

        System.out.println(nums.stream().filter(n -> n > 0).count());
    }
}