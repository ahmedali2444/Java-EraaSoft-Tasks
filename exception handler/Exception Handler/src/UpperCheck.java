public class UpperCheck {
    static void makeUpper(String str) {
        try {
            System.out.println(str.toUpperCase());
        } catch (NullPointerException e) {
            System.out.println("the string is null, nothing to print");
        }
    }

    public static void main(String[] args) {
        makeUpper(null);
    }
}
