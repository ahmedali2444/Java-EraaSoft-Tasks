package captaindata;

public class CaptainBenfit {
    public int recordId;
    public String captainBenefit;
    protected String allCaptainBenefits;

    public CaptainBenfit(int recordId, String captainBenefit, String allCaptainBenefits) {
        this.recordId = recordId;
        this.captainBenefit = captainBenefit;
        this.allCaptainBenefits = allCaptainBenefits;
    }
}
