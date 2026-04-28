import java.util.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) {
        // Main integer list used in most stream examples.
        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);

        // Main names list includes normal names, an empty string, and null for filtering tasks.
        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);

        // Student data used for grouping and pass/fail partitioning.
        List<Student> students = Arrays.asList(
                new Student("Ali", "IT", 85),
                new Student("Mona", "CS", 92),
                new Student("Ahmed", "IT", 60),
                new Student("Sara", "CS", 70),
                new Student("Omar", "IS", 45),
                new Student("Laila", "IS", 78)
        );

        // Employee data used for grouping, salary average, and sorting tasks.
        List<Employee> employees = Arrays.asList(
                new Employee("Ali", 30, "HR", 5000),
                new Employee("Mona", 25, "IT", 7000),
                new Employee("Ahmed", 30, "HR", 5500),
                new Employee("Sara", 27, "IT", 7200),
                new Employee("Omar", 40, "Finance", 8000),
                new Employee("Laila", 35, "Finance", 8200)
        );

        // Nested words list used to demonstrate flatMap.
        List<List<String>> nestedWords = Arrays.asList(
                Arrays.asList("Java", "Stream"),
                Arrays.asList("API", "Lambda"),
                Arrays.asList("FlatMap", "Map")
        );

        System.out.println("Basic Stream Operations");

        // 1. Filter even numbers from the integer list.
        List<Integer> evenNumbers = numbers.stream()
                .filter(number -> number % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("1. Even numbers: " + evenNumbers);

        // 2. Find all non-null names starting with a specific letter.
        List<String> namesStartingWithA = names.stream()
                .filter(Objects::nonNull)
                .filter(name -> name.startsWith("A"))
                .collect(Collectors.toList());
        System.out.println("2. Names starting with A: " + namesStartingWithA);

        // 3. Convert all non-null strings to uppercase.
        List<String> uppercasedNames = names.stream()
                .filter(Objects::nonNull)
                .filter(name -> !name.isEmpty())
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("3. Uppercase strings: " + uppercasedNames);

        // 4. Sort numbers in descending order.
        List<Integer> descendingNumbers = numbers.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println("4. Numbers descending: " + descendingNumbers);

        // 5. Remove duplicate numbers using distinct().
        List<Integer> distinctNumbers = numbers.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("5. Distinct numbers: " + distinctNumbers);

        System.out.println();
        System.out.println("Intermediate Stream Tasks");

        // 6. Count non-null strings longer than 5 characters.
        long stringsLongerThanFive = names.stream()
                .filter(Objects::nonNull)
                .filter(name -> name.length() > 5)
                .count();
        System.out.println("6. Strings longer than 5 characters: " + stringsLongerThanFive);

        // 7. Find the first number that matches the condition: divisible by 3.
        Optional<Integer> firstNumberDivisibleByThree = numbers.stream()
                .filter(number -> number % 3 == 0)
                .findFirst();
        System.out.println("7. First number divisible by 3: " + firstNumberDivisibleByThree.orElse(-1));

        // 8. Check if any number is divisible by 5.
        boolean anyDivisibleByFive = numbers.stream()
                .anyMatch(number -> number % 5 == 0);
        System.out.println("8. Any number divisible by 5: " + anyDivisibleByFive);

        // 9. Collect numbers into a Set instead of a List.
        Set<Integer> numbersSet = numbers.stream()
                .collect(Collectors.toSet());
        System.out.println("9. Numbers collected into Set: " + numbersSet);

        // 10. Skip the first 3 numbers and return the rest.
        List<Integer> afterSkippingFirstThree = numbers.stream()
                .skip(3)
                .collect(Collectors.toList());
        System.out.println("10. After skipping first 3: " + afterSkippingFirstThree);

        System.out.println();
        System.out.println("Numeric Streams & Reductions");

        // 11. Calculate the sum using reduce.
        int sum = numbers.stream()
                .reduce(0, (first, second) -> first + second);
        System.out.println("11. Sum using reduce: " + sum);

        // 12. Find the maximum and minimum values in the list.
        int maxNumber = numbers.stream()
                .max(Integer::compareTo)
                .orElseThrow(() -> new NoSuchElementException("No maximum number found"));
        int minNumber = numbers.stream()
                .min(Integer::compareTo)
                .orElseThrow(() -> new NoSuchElementException("No minimum number found"));
        System.out.println("12. Maximum and minimum values: max = " + maxNumber + ", min = " + minNumber);

        // 13. Calculate the average from a list of doubles.
        List<Double> doubleNumbers = numbers.stream()
                .map(Integer::doubleValue)
                .collect(Collectors.toList());
        OptionalDouble average = doubleNumbers.stream()
                .mapToDouble(Double::doubleValue)
                .average();
        System.out.println("13. Average of doubles: " + String.format("%.2f", average.orElse(0.0)));

        // 14. Multiply all non-zero integers together using reduce.
        long multiplication = numbers.stream()
                .filter(number -> number != 0)
                .mapToLong(Integer::longValue)
                .reduce(1L, (first, second) -> first * second);
        System.out.println("14. Multiplication using reduce: " + multiplication);

        // 15. Count how many numbers are positive.
        long positiveNumbersCount = numbers.stream()
                .filter(number -> number > 0)
                .count();
        System.out.println("15. Positive numbers count: " + positiveNumbersCount);

        System.out.println();
        System.out.println("Collectors & Grouping");

        // 16. Group students by department.
        Map<String, List<Student>> studentsByDepartment = students.stream()
                .collect(Collectors.groupingBy(Student::getDepartment));
        System.out.println("16. Students by department: " + studentsByDepartment);

        // 17. Partition numbers into even and odd groups.
        Map<Boolean, List<Integer>> evenOddPartition = numbers.stream()
                .collect(Collectors.partitioningBy(number -> number % 2 == 0));
        System.out.println("17. Even numbers: " + evenOddPartition.get(true));
        System.out.println("17. Odd numbers: " + evenOddPartition.get(false));

        // 18. Create a comma-separated string from valid names.
        String commaSeparatedNames = names.stream()
                .filter(Objects::nonNull)
                .filter(name -> !name.isEmpty())
                .collect(Collectors.joining(", "));
        System.out.println("18. Comma-separated names: " + commaSeparatedNames);

        // 19. Group employees by age and count how many employees are in each age.
        Map<Integer, Long> employeesCountByAge = employees.stream()
                .collect(Collectors.groupingBy(Employee::getAge, Collectors.counting()));
        System.out.println("19. Employees count by age: " + employeesCountByAge);

        // 20. Calculate the average salary for each department.
        Map<String, Double> averageSalaryByDepartment = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));
        System.out.println("20. Average salary by department: " + averageSalaryByDepartment);

        System.out.println();
        System.out.println("Optional, Map, FlatMap");

        // 21. Flatten the nested list into a single list.
        List<String> flattenedWords = nestedWords.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println("21. Flattened words: " + flattenedWords);

        // 22. Extract all unique characters from the flattened words.
        Set<Character> uniqueCharacters = nestedWords.stream()
                .flatMap(Collection::stream)
                .flatMap(word -> word.chars().mapToObj(characterCode -> (char) characterCode))
                .collect(Collectors.toSet());
        System.out.println("22. Unique characters: " + uniqueCharacters);

        // 23. Filter Optional values and collect only non-empty values.
        List<Optional<String>> optionalNames = Arrays.asList(
                Optional.of("Hello"),
                Optional.empty(),
                Optional.of("World"),
                Optional.empty()
        );
        List<String> nonEmptyOptionalValues = optionalNames.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        System.out.println("23. Non-empty optional values: " + nonEmptyOptionalValues);

        // 24. Map each non-null string to its length.
        List<Integer> stringLengths = names.stream()
                .filter(Objects::nonNull)
                .filter(name -> !name.isEmpty())
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println("24. String lengths: " + stringLengths);

        // 25. Return uppercased words that start with "A".
        List<String> uppercasedWordsStartingWithA = names.stream()
                .filter(Objects::nonNull)
                .filter(name -> name.startsWith("A"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("25. Uppercased words starting with A: " + uppercasedWordsStartingWithA);

        System.out.println();
        System.out.println("Advanced Operations");

        // 26. Sort employees by salary, then by name.
        List<Employee> employeesSortedBySalaryThenName = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).thenComparing(Employee::getName))
                .collect(Collectors.toList());
        System.out.println("26. Employees sorted by salary then name: " + employeesSortedBySalaryThenName);

        // 27. Find the second highest number after removing duplicates.
        Integer secondHighestNumber = numbers.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(null);
        System.out.println("27. Second highest number: " + secondHighestNumber);

        // 28. Find duplicate numbers by tracking which numbers were already seen.
        Set<Integer> seenNumbers = new HashSet<>();
        Set<Integer> duplicateNumbers = numbers.stream()
                .filter(number -> !seenNumbers.add(number))
                .collect(Collectors.toSet());
        System.out.println("28. Duplicate numbers: " + duplicateNumbers);

        // 29. Remove null and empty strings from the names list.
        List<String> namesWithoutNullOrEmpty = names.stream()
                .filter(Objects::nonNull)
                .filter(name -> !name.isEmpty())
                .collect(Collectors.toList());
        System.out.println("29. Names without null or empty strings: " + namesWithoutNullOrEmpty);

        // 30. Partition students into pass/fail groups based on grade 60.
        Map<Boolean, List<Student>> studentsPassFail = students.stream()
                .collect(Collectors.partitioningBy(student -> student.getGrade() >= 60));
        System.out.println("30. Pass students: " + studentsPassFail.get(true));
        System.out.println("30. Fail students: " + studentsPassFail.get(false));
    }
}
