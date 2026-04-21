package level2;

import java.util.HashSet;
import java.util.List;

public class TestDifferentEquality {
    public static void main(String[] args) {
        List<Integer> ids = List.of(1, 1, 2, 3, 4, 4);
        List<String> names = List.of("Ali", "Ibrahim", "Ali", "Sara", "Sara", "Sara");

        HashSet<PersonById> byId = new HashSet<>();
        HashSet<PersonByName> byName = new HashSet<>();
        HashSet<PersonByIdAndName> byIdAndName = new HashSet<>();

        for (int i = 0; i < ids.size(); i++) {
            int id = ids.get(i);
            String name = names.get(i);
            byId.add(new PersonById(id, name));
            byName.add(new PersonByName(id, name));
            byIdAndName.add(new PersonByIdAndName(id, name));
        }

        System.out.println("Same logical data, different equality rules:");
        System.out.println("Original records: (1,Ali), (1,Ibrahim), (2,Ali), (3,Sara), (4,Sara), (4,Sara)");
        System.out.println("By id only -> size " + byId.size() + " -> " + byId);
        System.out.println("By name only -> size " + byName.size() + " -> " + byName);
        System.out.println("By id and name -> size " + byIdAndName.size() + " -> " + byIdAndName);
        System.out.println("Changing the equality rule changes HashSet behavior directly.");
    }
}
