public class MoneyTaker extends Thread {
    private MoneyBox box;

    public MoneyTaker(MoneyBox box) {
        this.box = box;
    }

    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                box.takeMoney(150);
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {}
    }
}
