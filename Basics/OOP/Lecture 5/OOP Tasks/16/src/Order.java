import java.util.ArrayList;

public class Order {
    int orderId;
    ArrayList<Food> foodList = new ArrayList<>();

    public Order(int orderId) {
        this.orderId = orderId;
    }

    public void addFood(Food foodItem) {
        foodList.add(foodItem);
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (Food foodItem : foodList) {
            totalPrice += foodItem.foodPrice;
        }
        return totalPrice;
    }
}
