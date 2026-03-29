public class Player extends Info {
    private int number;

    void setNumber(int number) {
        if (number <= 99 && number >= 1) {
            this.number = number;
        } else {
            System.out.println("number must be between 1 and 99");
        }
    }

    int getNumber() {
        return number;
    }
}
