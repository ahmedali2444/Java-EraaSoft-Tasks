import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);

        List<List<String>> nested = Arrays.asList(
                Arrays.asList("Java", "Stream"),
                Arrays.asList("API", "Lambda"),
                Arrays.asList("FlatMap", "Map")
        );

        System.out.println(nested.stream().flatMap(List::stream).collect(Collectors.toList()));

        System.out.println(nested.stream().flatMap(List::stream).flatMapToInt(String::chars).mapToObj(c -> (char) c).collect(Collectors.toSet()));

        List<Optional<String>> opts = Arrays.asList(Optional.of("Ali"), Optional.empty(), Optional.of("Sara"), Optional.empty());
        System.out.println(opts.stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList()));

        System.out.println(words.stream().filter(n -> n != null && !n.isEmpty()).map(String::length).collect(Collectors.toList()));

        System.out.println(words.stream().filter(n -> n != null && n.startsWith("A")).map(String::toUpperCase).collect(Collectors.toList()));
    }
}