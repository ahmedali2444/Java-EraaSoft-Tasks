public class Main {
    public static void main(String[] args) {
        App firstApp = new App();

        firstApp.setNum1(4);
        firstApp.setNum2(6);
        firstApp.setNum3(8);

        System.out.println(firstApp.getNum1());
        System.out.println(firstApp.getNum2());
        System.out.println(firstApp.getNum3());
        System.out.println(firstApp.sum());

        System.out.println("----------------");

        App secondApp = new App();

        secondApp.setNum1(3);
        secondApp.setNum2(5);
        secondApp.setNum3(10);

        System.out.println(secondApp.getNum1());
        System.out.println(secondApp.getNum2());
        System.out.println(secondApp.getNum3());
        System.out.println(secondApp.sum());
    }
}
