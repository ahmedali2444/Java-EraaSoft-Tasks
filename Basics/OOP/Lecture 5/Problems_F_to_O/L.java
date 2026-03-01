package Problems_F_to_O;


class ProblemL{
    private String name1;
    private String name2;

    public ProblemL(String name1 , String name2){
        this.name1 = name1;
        this.name2 = name2;
    }

    public void showResult(){
        String lastn1 = name1.split(" ")[1];
        String lastn2 = name2.split(" ")[1];

        if (lastn1.equals(lastn2))
            System.out.println("ARE Brothers");
        else
            System.out.println("NOT");
    }
}
public class L {

    public static void main(String[] args){

        ProblemL Pl = new ProblemL("ahmed ali" , "omar ali");
        Pl.showResult();

        ProblemL Pl2 = new ProblemL("ahmed osama" , "omar ali");
        Pl2.showResult();
    }
}
