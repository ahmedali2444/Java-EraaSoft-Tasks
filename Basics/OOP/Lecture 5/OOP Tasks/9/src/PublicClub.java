public class PublicClub extends Club {
    public PublicClub(String name) {
        super(name);
    }

    public void showPlayers() {
        System.out.println("Public club: " + clubName);
        for (Player playerItem : playerList) {
            System.out.println("Player ID: " + playerItem.playerId + " and Name: " + playerItem.playerName);
        }
    }
}
