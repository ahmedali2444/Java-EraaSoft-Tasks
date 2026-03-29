public class Player {
    private int playerId;
    private String playerName;
    private int shirtNumber;

    public Player(int playerId, String playerName, int shirtNumber) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.shirtNumber = shirtNumber;
    }

    public int getId() {
        return playerId;
    }

    public void setId(int id) {
        this.playerId = id;
    }

    public String getName() {
        return playerName;
    }

    public void setName(String name) {
        this.playerName = name;
    }

    public int getNumber() {
        return shirtNumber;
    }

    public void setNumber(int number) {
        this.shirtNumber = number;
    }

    public String display() {
        return "ID: " + playerId + ", Name: " + playerName + ", Number: " + shirtNumber;
    }
}
