import java.util.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        List<String> words = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);

        List<Person> people = Arrays.asList(
                new Person("Ali", "IT", 85), new Person("Mona", "CS", 92),
                new Person("Ahmed", "IT", 60), new Person("Sara", "CS", 70),
                new Person("Omar", "IS", 45), new Person("Laila", "IS", 78)
        );

        List<Staff> staff = Arrays.asList(
                new Staff("Ali", 30, "HR", 5000), new Staff("Mona", 25, "IT", 7000),
                new Staff("Ahmed", 30, "HR", 5500), new Staff("Sara", 27, "IT", 7200),
                new Staff("Omar", 40, "Finance", 8000), new Staff("Laila", 35, "Finance", 8200)
        );

        System.out.println(people.stream().collect(Collectors.groupingBy(Person::getDep)));

        System.out.println(nums.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0)));

        System.out.println(words.stream().filter(n -> n != null && !n.isEmpty()).collect(Collectors.joining(", ")));

        System.out.println(staff.stream().collect(Collectors.groupingBy(Staff::getAge, Collectors.counting())));

        System.out.println(staff.stream().collect(Collectors.groupingBy(Staff::getDep, Collectors.averagingDouble(Staff::getPay))));
    }
}