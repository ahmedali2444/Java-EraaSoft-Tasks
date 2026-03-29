public class RestaurantController extends Restaurant {
    public void showOrders() {
        for (Order currentOrder : orderList) {
            System.out.println("Order ID: " + currentOrder.orderId + " , Item: " + currentOrder.itemName);
        }
    }
}
