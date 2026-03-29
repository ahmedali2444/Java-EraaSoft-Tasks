import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();
        String userName = input.next();

        App userApp = new App();
        userApp.setName(userName);
        userApp.setNum(number);

        System.out.println(userApp.getName());
        System.out.println(userApp.getNum());
    }
}
