public class ClupFc extends Player {
    private String fcCode;

    public ClupFc(int id, String name, int number, String fcCode) {
        super(id, name, number);
        this.fcCode = fcCode;
    }

    public String getFcode() {
        return fcCode;
    }

    public void setFcode(String fcCode) {
        this.fcCode = fcCode;
    }

    @Override
    public String display() {
        return super.display() + ", FC Code: " + fcCode;
    }
}
