public class Main {
    public static void main(String[] args) {
        RestaurantController restaurantController = new RestaurantController();
        restaurantController.addOrder(new Order(1, "Pizza"));
        restaurantController.addOrder(new Order(2, "Burger"));
        restaurantController.addOrder(new Order(3, "Pasta"));
        restaurantController.showOrders();
    }
}
