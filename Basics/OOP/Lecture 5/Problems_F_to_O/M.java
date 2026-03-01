package Problems_F_to_O;


class ProblemM{
    private  char ch;

    public ProblemM(char ch ){
        this.ch = ch;
    }
    public void ShowResult(){
        if(ch >= 'A' && ch <= 'Z')
            System.out.println("ALPHA\n" + "IS CAPITAL");
        else if(ch >= 'a' && ch <= 'z')
            System.out.println("ALPHA\n" + "IS SMALL");
        else if(ch >= '0' && ch <= '9')
            System.out.println("IS DIGIT");
    }

}
public class M {
    public static void main(String[] args){
        new ProblemM('a').ShowResult();
        new ProblemM('A').ShowResult();
        new ProblemM('9').ShowResult();
    }
}
