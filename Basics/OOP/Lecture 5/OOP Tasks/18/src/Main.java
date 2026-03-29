import playerdata.PlayerBenfit;
import captaindata.CaptainBenfit;

public class Main {
    static class MyPlayer extends PlayerBenfit {
        public MyPlayer(int id, String playerBenefit, String allPlayerBenefits) {
            super(id, playerBenefit, allPlayerBenefits);
        }
    }

    static class MyCaptain extends CaptainBenfit {
        public MyCaptain(int id, String captainBenefit, String allCaptainBenefits) {
            super(id, captainBenefit, allCaptainBenefits);
        }
    }

    public static void main(String[] args) {
        MyPlayer playerData = new MyPlayer(1, "Bonus", "All Benefits");
        System.out.println(playerData.recordId);
        System.out.println(playerData.playerBenefit);

        MyCaptain captainData = new MyCaptain(1, "benf", "All Benefits");
        System.out.println(captainData.recordId);
        System.out.println(captainData.captainBenefit);
    }
}
