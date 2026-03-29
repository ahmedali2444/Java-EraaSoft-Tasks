package playerdata;

public class Main extends PlayerBenfit {
    public Main(int id, String playerBenefit, String allPlayerBenefits) {
        super(id, playerBenefit, allPlayerBenefits);
    }

    public static void main(String[] args) {
        Main playerMain = new Main(1, "Bonus", "All Benefits");
        System.out.println(playerMain.recordId);
        System.out.println(playerMain.playerBenefit);
        System.out.println(playerMain.allPlayerBenefits);
    }
}
