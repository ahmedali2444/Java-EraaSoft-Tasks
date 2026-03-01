package OopTask_1_to_7;


class Player{
    private String name ;
    private int number ;

    public void setName(String playerName){
        if (playerName.length() > 5)
            name = playerName ;
        else
            System.out.println("Name Length Should be Greater than 5 digits");
    }
    public void setNumber(int Number){
        if (Number > 0)
            number = Number ;
        else
            System.out.println("Number should be greater than 0");
    }
    public void showInfo(){
        System.out.println("name : " + name);
        System.out.println("number : " + number);
    }
}
public class task2_1 {
    public static void main(String[] args){
        Player p = new Player();
        p.setName("ahmed ali");
        p.setNumber(100);
        p.showInfo();
    }
}