public class MoneyAdder extends Thread {
    private MoneyBox box;

    public MoneyAdder(MoneyBox box) {
        this.box = box;
    }

    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                box.addMoney(200);
                Thread.sleep(1500);
            }
        } catch (InterruptedException e) {}
    }
}
