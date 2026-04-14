public class AgeCheck {
    static class BadAgeError extends Exception {
        BadAgeError(String msg) {
            super(msg);
        }
    }

    static void checkAge(int age) throws BadAgeError {
        if (age < 18) {
            throw new BadAgeError("you need to be at least 18");
        }
        System.out.println("you are allowed in");
    }

    public static void main(String[] args) {
        try {
            checkAge(15);
        } catch (BadAgeError e) {
            System.out.println(e.getMessage());
        }
    }
}
