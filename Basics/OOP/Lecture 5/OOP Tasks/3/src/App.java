public class App {
    private int firstNum;
    private int secondNum;
    private int thirdNum;

    public int getNum1() {
        return firstNum;
    }

    public void setNum1(int value) {
        if (value % 2 == 0) {
            this.firstNum = value;
        }
    }

    public int getNum2() {
        return secondNum;
    }

    public void setNum2(int value) {
        if (value % 2 == 0) {
            this.secondNum = value;
        }
    }

    public int getNum3() {
        return thirdNum;
    }

    public void setNum3(int value) {
        if (value % 2 == 0) {
            this.thirdNum = value;
        }
    }

    public int sum() {
        return firstNum + secondNum + thirdNum;
    }
}
