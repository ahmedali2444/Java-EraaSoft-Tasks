import java.util.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        List<String> words = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);

        List<Kid> kids = Arrays.asList(
                new Kid("Ali", "IT", 85), new Kid("Mona", "CS", 92),
                new Kid("Ahmed", "IT", 60), new Kid("Sara", "CS", 70),
                new Kid("Omar", "IS", 45), new Kid("Laila", "IS", 78)
        );

        List<Worker> workers = Arrays.asList(
                new Worker("Ali", 30, "HR", 5000), new Worker("Mona", 25, "IT", 7000),
                new Worker("Ahmed", 30, "HR", 5500), new Worker("Sara", 27, "IT", 7200),
                new Worker("Omar", 40, "Finance", 8000), new Worker("Laila", 35, "Finance", 8200)
        );

        System.out.println(workers.stream().sorted(Comparator.comparingDouble(Worker::getPay).thenComparing(Worker::getNm)).collect(Collectors.toList()));

        System.out.println(nums.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst().orElse(-1));

        Set<Integer> found = new HashSet<>();
        System.out.println(nums.stream().filter(n -> !found.add(n)).distinct().collect(Collectors.toList()));

        System.out.println(words.stream().filter(n -> n != null && !n.isEmpty()).collect(Collectors.toList()));

        System.out.println(kids.stream().collect(Collectors.partitioningBy(k -> k.getMark() >= 50)));
    }
}