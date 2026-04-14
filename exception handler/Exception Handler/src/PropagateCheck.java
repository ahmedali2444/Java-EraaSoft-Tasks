public class PropagateCheck {
    static void step1() {
        int res = 10 / 0;
    }

    static void step2() {
        step1();
    }

    public static void main(String[] args) {
        try {
            step2();
        } catch (ArithmeticException e) {
            System.out.println("caught it in main: " + e.getMessage());
        }
    }
}
