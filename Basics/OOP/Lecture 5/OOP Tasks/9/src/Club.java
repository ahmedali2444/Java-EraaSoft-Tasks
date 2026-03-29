import java.util.ArrayList;

public abstract class Club {
    String clubName;
    ArrayList<Player> playerList = new ArrayList<>();

    public Club(String clubName) {
        this.clubName = clubName;
    }

    public void addPlayer(Player playerItem) {
        playerList.add(playerItem);
    }

    public abstract void showPlayers();
}
