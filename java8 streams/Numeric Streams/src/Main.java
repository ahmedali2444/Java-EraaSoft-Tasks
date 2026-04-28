import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        List<Double> decimalNumbers = Arrays.asList(10.0, 5.0, 3.0, 7.0, 2.0, 10.0, 5.0, 8.0, 9.0, 0.0, -3.0, 4.0);

        // Adding all numbers together using reduce.
        int sum = numbers.stream()
                .reduce(0, (firstNumber, secondNumber) -> firstNumber + secondNumber);
        System.out.println("Sum of numbers: " + sum);

        // Getting the biggest number in the list.
        int maxNumber = numbers.stream()
                .max((firstNumber, secondNumber) -> firstNumber.compareTo(secondNumber))
                .orElseThrow(() -> new NoSuchElementException("No maximum number found"));

        // Getting the smallest number in the list.
        int minNumber = numbers.stream()
                .min((firstNumber, secondNumber) -> firstNumber.compareTo(secondNumber))
                .orElseThrow(() -> new NoSuchElementException("No minimum number found"));
        System.out.println("Maximum number: " + maxNumber);
        System.out.println("Minimum number: " + minNumber);

        // Calculating the average from a list of double numbers.
        double average = decimalNumbers.stream()
                .mapToDouble(number -> number)
                .average()
                .orElse(0.0);
        System.out.println("Average of decimal numbers: " + average);

        // Multiplying all numbers together using reduce.
        int multiplicationResult = numbers.stream()
                .reduce(1, (firstNumber, secondNumber) -> firstNumber * secondNumber);
        System.out.println("Multiplication result: " + multiplicationResult);

        // Counting only numbers that are greater than zero.
        long positiveNumbersCount = numbers.stream()
                .filter(number -> number > 0)
                .count();
        System.out.println("Count of positive numbers: " + positiveNumbersCount);
    }
}
