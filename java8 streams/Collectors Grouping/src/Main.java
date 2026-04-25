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

        // Grouping the students by their department.
        Map<String, List<Student>> studentsByDepartment = students.stream()
                .collect(Collectors.groupingBy(student -> student.getDepartment()));
        System.out.println("Students by department: " + studentsByDepartment);

        // Splitting the numbers into two groups: even numbers and odd numbers.
        Map<Boolean, List<Integer>> numbersPartitionedByEvenOdd = numbers.stream()
                .collect(Collectors.partitioningBy(number -> number % 2 == 0));
        System.out.println("Partitioned numbers (true = even): " + numbersPartitionedByEvenOdd);

        // Joining the valid names in one string separated by commas.
        String commaSeparatedNames = names.stream()
                .filter(name -> name != null)
                .filter(name -> !name.isEmpty())
                .collect(Collectors.joining(", "));
        System.out.println("Comma-separated names: " + commaSeparatedNames);

        // Grouping employees by age, then counting how many employees are in each age.
        Map<Integer, Long> employeesCountByAge = employees.stream()
                .collect(Collectors.groupingBy(employee -> employee.getAge(), Collectors.counting()));
        System.out.println("Employees count by age: " + employeesCountByAge);

        // Grouping employees by department, then calculating the average salary for each one.
        Map<String, Double> averageSalaryByDepartment = employees.stream()
                .collect(Collectors.groupingBy(
                        employee -> employee.getDepartment(),
                        Collectors.averagingDouble(employee -> employee.getSalary())
                ));
        System.out.println("Average salary by department: " + averageSalaryByDepartment);
    }
}
