public class PrivateClub extends Club {
    public PrivateClub(String name) {
        super(name);
    }

    public void showPlayers() {
        System.out.println("Private club: " + clubName);
        for (Player playerItem : playerList) {
            System.out.println("Player ID: " + playerItem.playerId + " and Name: " + playerItem.playerName);
        }
    }
}
