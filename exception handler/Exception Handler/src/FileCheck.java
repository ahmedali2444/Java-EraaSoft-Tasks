import java.io.*;
import java.util.Scanner;

public class FileCheck {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new FileReader("test.txt"));
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("could not find the file");
        }
    }
}
