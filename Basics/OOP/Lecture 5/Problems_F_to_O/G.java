package Problems_F_to_O;


class ProblemG{
    private  int num;

    public ProblemG(int num){
        this.num = num;
    }

    public void ShowSum1ToN(){
        int sum = (num * (num+1))/2;
        System.out.println("SUM: " + sum);
    }

}
public class G {
    public static void main(String[] args){

        ProblemG Pg = new ProblemG(3);
        Pg.ShowSum1ToN();

    }
}
