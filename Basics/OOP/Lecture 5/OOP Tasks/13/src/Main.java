public class Main {
    public static void main(String[] args) {
        Shirt playerShirt = new Shirt(10, "Red");
        Player currentPlayer = new Player(1, "Ahmed", playerShirt);
        currentPlayer.showPlayer();
    }
}
