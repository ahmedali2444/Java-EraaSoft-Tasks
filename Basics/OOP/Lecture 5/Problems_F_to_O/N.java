package Problems_F_to_O;


class ProblemN{
    private  char ch;

    public ProblemN(char ch ){
        this.ch = ch;
    }
    public void ShowResult(){
        if(ch >= 'A' && ch <= 'Z')
            System.out.println((char)(ch + 32));
        else if(ch >= 'a' && ch <= 'z')
            System.out.println((char)(ch - 32));

    }

}
public class N {
    public static void main(String[] args){
        new ProblemN('a').ShowResult();
        new ProblemN('A').ShowResult();
    }
}
