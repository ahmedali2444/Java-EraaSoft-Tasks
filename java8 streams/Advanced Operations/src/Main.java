import java.util.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);

        List<Student> students = Arrays.asList(
                new Student("Ali", "IT", 85), new Student("Mona", "CS", 92),
                new Student("Ahmed", "IT", 60), new Student("Sara", "CS", 70),
                new Student("Omar", "IS", 45), new Student("Laila", "IS", 78)
        );

        List<Employee> employees = Arrays.asList(
                new Employee("Ali", 30, "HR", 5000), new Employee("Mona", 25, "IT", 7000),
                new Employee("Ahmed", 30, "HR", 5500), new Employee("Sara", 27, "IT", 7200),
                new Employee("Omar", 40, "Finance", 8000), new Employee("Laila", 35, "Finance", 8200)
        );

        // Sorting employees by salary first, then by name if salaries are equal.
        List<Employee> employeesSortedBySalaryThenName = employees.stream()
                .sorted(Comparator.comparingDouble((Employee employee) -> employee.getSalary())
                        .thenComparing(employee -> employee.getName()))
                .collect(Collectors.toList());
        System.out.println("Employees sorted by salary then name: " + employeesSortedBySalaryThenName);

        // Removing repeated numbers first, then sorting descending and skipping the biggest one.
        int secondHighestNumber = numbers.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(-1);
        System.out.println("Second highest number: " + secondHighestNumber);

        // Using a Set to know if the number appeared before, then collecting duplicates.
        Set<Integer> found = new HashSet<>();
        List<Integer> duplicateNumbers = numbers.stream()
                .filter(number -> !found.add(number))
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Duplicate numbers: " + duplicateNumbers);

        // Removing null values and empty strings from the names list.
        List<String> validNames = names.stream()
                .filter(name -> name != null)
                .filter(name -> !name.isEmpty())
                .collect(Collectors.toList());
        System.out.println("Names after removing null/empty values: " + validNames);

        // Putting students with grade 50 or more in pass, and the others in fail.
        Map<Boolean, List<Student>> studentsPartitionedByPassFail = students.stream()
                .collect(Collectors.partitioningBy(student -> student.getGrade() >= 50));
        System.out.println("Students partitioned by pass/fail: " + studentsPartitionedByPassFail);
    }
}
