import java.util.Scanner;

public class ArrayCheck {
    public static void main(String[] args) {
        int[] nums = {10, 20, 30, 40, 50};
        Scanner sc = new Scanner(System.in);
        int idx = sc.nextInt();
        try {
            System.out.println(nums[idx]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("that index does not exist in the array");
        }
    }
}
