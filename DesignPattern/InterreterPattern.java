import java.util.HashMap;
import java.util.Map;

public class InterreterPattern {
    public static void main(String[] args) {
        Contaxt con=new Contaxt();
        con.setItem("a", new Var("az"));
        con.setItem("b", new Var("bz"));
        con.setItem("d", new Var("dz"));
        con.setItem("c", new Var("cz"));

        PLUS plus1=new PLUS(new Var("a"),new Var("b"));
        PLUS plus2=new PLUS(new Var("d"),new Var("c"));
        PLUS plus=new PLUS(plus1,plus2);

        plus.interpret(con);
    }
}

class Contaxt{
    Map<String,Expr> map=new HashMap<String, Expr>();

    Expr getExpr(String str){
        return map.get(str);
    }

    void setItem(String name, Expr e){
        map.put(name,e);
    }
}

interface Expr{
    void interpret(Contaxt context);
    void getName();
}
class Var implements Expr{

    String name;

    Var(String name) {
        this.name = name;
    }

    public void getName(){
        System.out.print(name);
    }
    public void interpret(Contaxt context) {
      context.getExpr(name).getName();
    }

}

class Num implements Expr{

    int num;

    Num(int num) {
        this.num = num;
    }

    public void interpret(Contaxt context) {
       context.getExpr("" + num).getName();
    }

    @Override
    public void getName() {
        System.out.print(num);
    }
}

class PLUS implements Expr{

    private Expr e1;
    private Expr e2;

    PLUS(Expr e1, Expr e2) {
        this.e1=e1;
        this.e2=e2;
    }

    public void interpret(Contaxt context) {

        System.out.print("(");
        e1.interpret(context);
        System.out.print("+");
        e2.interpret(context);
        System.out.print(")");
    }

    @Override
    public void getName() {
        e1.getName();
        e2.getName();

    }
}
