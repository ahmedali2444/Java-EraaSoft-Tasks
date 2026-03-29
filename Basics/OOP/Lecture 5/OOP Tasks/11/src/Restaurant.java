import java.util.ArrayList;

public abstract class Restaurant {
    ArrayList<Order> orderList = new ArrayList<>();

    public void addOrder(Order order) {
        orderList.add(order);
    }

    public abstract void showOrders();
}
