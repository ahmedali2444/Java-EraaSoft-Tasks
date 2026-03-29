package captaindata;

public class Main extends CaptainBenfit {
    public Main(int id, String captainBenefit, String allCaptainBenefits) {
        super(id, captainBenefit, allCaptainBenefits);
    }

    public static void main(String[] args) {
        Main captainMain = new Main(1, "Armband", "All Benefits");
        System.out.println(captainMain.recordId);
        System.out.println(captainMain.captainBenefit);
        System.out.println(captainMain.allCaptainBenefits);
    }
}
