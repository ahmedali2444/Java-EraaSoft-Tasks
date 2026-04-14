public class MoneyBox {
    private int total = 0;

    public synchronized void addMoney(int val) {
        total += val;
        System.out.println("Mahmoud put in " + val + " and now we have " + total);
        notify();
    }

    public synchronized void takeMoney(int val) throws InterruptedException {
        while (total < val) {
            System.out.println("not enough yet, holding on");
            wait();
        }
        total -= val;
        System.out.println("Ali took out " + val + " and now we have " + total);
    }
}
