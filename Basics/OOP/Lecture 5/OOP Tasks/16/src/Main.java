public class Main {
    public static void main(String[] args) {
        Food firstFood = new Food(1, "Pizza", 50.0);
        Food secondFood = new Food(2, "Burger", 30.0);
        Food thirdFood = new Food(3, "Pasta", 40.0);

        Order currentOrder = new Order(1);
        currentOrder.addFood(firstFood);
        currentOrder.addFood(secondFood);
        currentOrder.addFood(thirdFood);

        Gift freeGift = new Gift(1, "Free Drink");

        Bill customerBill = new Bill(1, currentOrder, freeGift);

        Person currentPerson = new Person(1, "Ahmed", customerBill);
        currentPerson.showPerson();
    }
}
