import java.io.*;

public class FileReadCheck {
    static void openFile() throws IOException {
        FileReader fr = new FileReader("test.txt");
    }

    public static void main(String[] args) {
        try {
            openFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
