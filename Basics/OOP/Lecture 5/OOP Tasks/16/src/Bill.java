public class Bill {
    int billId;
    Order customerOrder;
    Gift giftItem;

    public Bill(int billId, Order customerOrder, Gift giftItem) {
        this.billId = billId;
        this.customerOrder = customerOrder;
        this.giftItem = giftItem;
    }

    public void showBill() {
        System.out.println("Bill ID: " + billId);
        System.out.println("Order ID: " + customerOrder.orderId);
        System.out.println("Food List:");
        for (Food foodItem : customerOrder.foodList) {
            System.out.println("  Food ID: " + foodItem.foodId + " and Name: " + foodItem.foodName + " , Price: " + foodItem.foodPrice);
        }
        System.out.println("Total Price: " + customerOrder.getTotalPrice());
        if (giftItem != null) {
            System.out.println("Gift: " + giftItem.giftName);
        }
    }
}
