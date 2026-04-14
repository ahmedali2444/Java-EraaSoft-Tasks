public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("part 1 extends thread");
        Thread th1 = new Thread() {
            public void run() {
                System.out.println("Ahmed says hi from the thread");
            }
        };
        th1.start();
        th1.join();

        System.out.println("\npart 2 implements runnable");
        Thread th2 = new Thread(() -> System.out.println("running in " + Thread.currentThread().getName()));
        th2.start();
        th2.join();

        System.out.println("\npart 3 thread sleep");
        for (int i = 1; i <= 5; i++) {
            System.out.println(i);
            Thread.sleep(1000);
        }

        System.out.println("\npart 4 thread join");
        Thread th3 = new Thread(() -> System.out.println("first thread is done"));
        Thread th4 = new Thread(() -> System.out.println("second thread is done"));
        th3.start();
        th4.start();
        th3.join();
        th4.join();
        System.out.println("all threads finished");

        System.out.println("\npart 5 bank account");
        MoneyBox box = new MoneyBox();
        MoneyAdder adder = new MoneyAdder(box);
        MoneyTaker taker = new MoneyTaker(box);
        adder.start();
        taker.start();
        adder.join();
        taker.join();
    }
}