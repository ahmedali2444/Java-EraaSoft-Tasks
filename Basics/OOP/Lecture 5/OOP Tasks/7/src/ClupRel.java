public class ClupRel extends Player {
    private String relCode;

    public ClupRel(int id, String name, int number, String relCode) {
        super(id, name, number);
        this.relCode = relCode;
    }

    public String getRcode() {
        return relCode;
    }

    public void setRcode(String relCode) {
        this.relCode = relCode;
    }

    @Override
    public String display() {
        return super.display() + ", REL Code: " + relCode;
    }
}
