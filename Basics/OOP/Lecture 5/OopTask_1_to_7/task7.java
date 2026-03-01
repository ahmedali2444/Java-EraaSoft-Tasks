package OopTask_1_to_7;


class Player7 {
    private int id;
    private String name;
    private int number;

    public Player7(int id, String name, int number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void showPlayerInfo() {
        System.out.println("ID: " + id);
        System.out.println("Number: " + number);
        System.out.println("Name: " + name);
    }
}

class ClupFc extends Player7 {
    private int fCode;

    public ClupFc(int id, String name, int number, int fCode) {
        super(id, name, number);
        this.fCode = fCode;
    }

    public int getfCode() {
        return fCode;
    }

    public void setfCode(int fCode) {
        this.fCode = fCode;
    }

    public void showPlayerDetails() {
        showPlayerInfo();
        System.out.println("FCode: " + fCode);
    }
}

class ClupRel extends Player7 {
    private int rCode;

    public ClupRel(int id, String name, int number, int rCode) {
        super(id, name, number);
        this.rCode = rCode;
    }

    public int getrCode() {
        return rCode;
    }

    public void setrCode(int rCode) {
        this.rCode = rCode;
    }

    public void showPlayerDetails() {
        showPlayerInfo();
        System.out.println("FCode: " + rCode);
    }

}

public class task7 {
    public static void main(String[] args) {
        int idFc = 1;
        String nameFc = "ahmed";
        int numberFc = 10;
        int fCode = 123;

        ClupFc playerFc = new ClupFc(idFc, nameFc, numberFc, fCode);

        int idRel = 2;
        String nameRel = "ali";
        int numberRel = 12;
        int rCode = 456;

        ClupRel playerRel = new ClupRel(idRel, nameRel, numberRel, rCode);

        System.out.println("Player Info:");
        playerFc.showPlayerInfo();

        System.out.println("\nFootball Club Player Details:");
        playerFc.showPlayerDetails();

        System.out.println("\nReligious Club Player Details:");
        playerRel.showPlayerDetails();
    }
}