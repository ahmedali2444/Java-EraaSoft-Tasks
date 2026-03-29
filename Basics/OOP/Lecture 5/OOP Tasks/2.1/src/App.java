public class App {
    private int storedNumber;
    private String userName;

    public void setNum(int number) {
        if (number > 0) {
            this.storedNumber = number;
        } else {
            System.out.println("number must be greater than 0");
        }
    }

    public void setName(String text) {
        if (text.length() > 5) {
            this.userName = text;
        } else {
            System.out.println("name length must be more than 5");
        }
    }

    public int getNum() {
        return storedNumber;
    }

    public String getName() {
        return userName;
    }
}
