public class Main {
    public static void main(String[] args) {
        ClupFc firstClubPlayer = new ClupFc(1, "yaseen", 10, "F123");
        ClupRel secondClubPlayer = new ClupRel(2, "ali", 7, "R456");

        System.out.println("ClupFc player: " + firstClubPlayer.display());
        System.out.println("ClupRel player: " + secondClubPlayer.display());
    }
}
