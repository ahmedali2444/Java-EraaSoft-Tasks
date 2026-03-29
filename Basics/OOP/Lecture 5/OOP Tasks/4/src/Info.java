public class Info {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0)
            this.id = id;
        else {
            System.out.println("id must be greater than 0");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
