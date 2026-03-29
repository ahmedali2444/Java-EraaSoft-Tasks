public class Main {
    public static void main(String[] args) {
        PublicClub communityClub = new PublicClub("Alahly");
        communityClub.addPlayer(new Player(1, "yaseen"));
        communityClub.addPlayer(new Player(2, "mohamed"));
        communityClub.showPlayers();

        PrivateClub vipClub = new PrivateClub("Zamalek");
        vipClub.addPlayer(new Player(3, "omar"));
        vipClub.addPlayer(new Player(4, "khaled"));
        vipClub.showPlayers();
    }
}
