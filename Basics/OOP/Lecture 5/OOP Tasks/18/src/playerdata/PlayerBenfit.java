package playerdata;

public class PlayerBenfit {
    public int recordId;
    public String playerBenefit;
    protected String allPlayerBenefits;

    public PlayerBenfit(int recordId, String playerBenefit, String allPlayerBenefits) {
        this.recordId = recordId;
        this.playerBenefit = playerBenefit;
        this.allPlayerBenefits = allPlayerBenefits;
    }
}
