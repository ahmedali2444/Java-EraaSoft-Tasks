public class NestedCheck {
    public static void main(String[] args) {
        try {
            System.out.println("we are in the outer try");
            try {
                int res = 10 / 0;
            } catch (ArithmeticException e) {
                System.out.println("caught inside: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("caught outside: " + e.getMessage());
        }
    }
}
