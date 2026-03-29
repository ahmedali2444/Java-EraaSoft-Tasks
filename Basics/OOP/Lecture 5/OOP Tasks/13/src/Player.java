public class Player {
    int playerId;
    String playerName;
    Shirt shirtInfo;

    public Player(int playerId, String playerName, Shirt shirtInfo) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.shirtInfo = shirtInfo;
    }

    public void showPlayer() {
        System.out.println("Player ID: " + playerId);
        System.out.println("Player Name: " + playerName);
        System.out.println("Shirt Number: " + shirtInfo.shirtNumber);
        System.out.println("Shirt Color: " + shirtInfo.shirtColor);
    }
}
