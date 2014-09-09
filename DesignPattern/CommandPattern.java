import java.util.ArrayList;
import java.util.List;

public class CommandPattern {
    public static void main(String[] args) {
        FRONTA f=new FRONTA();
        Number n=new Number();
        f.addCommand(new PLUS_MINUS(n));
        f.addCommand(new PLUS_NASOBIT(n));
        f.doCommands();

    }
}

// Reciever
class Number{
    int x=0;

    void plus(int y){
        x=x+y;
    }

    void minus(int y){
        x=x-y;
    }
    void nasobit(int c){
       x=x*c;
    }
    int getX(){
      return x;
    }
}


interface Command{
   void execute();
}

//Concreate Command
class PLUS_MINUS implements Command{
    Number n;

    PLUS_MINUS(Number n) {
        this.n = n;
    }

    @Override
    public void execute() {
       int c=n.getX();
       n.plus(10);
       n.minus(5);
       System.out.println(c+"+10-5="+n.getX());
    }
}

class PLUS_NASOBIT implements Command{
    Number n;

    PLUS_NASOBIT(Number n) {
        this.n = n;
    }

    @Override
    public void execute() {
        int c=n.getX();
        n.plus(20);
        n.nasobit(2);
        System.out.println("("+c+"+20)*2="+n.getX());
    }
}

// Invoker
class FRONTA{

    private List<Command> list=new ArrayList<Command>();

    FRONTA() {
    }

    void addCommand(Command c){
        list.add(c);
    }

    void doCommands(){
        for (int i = 0; i < list.size(); i++) {
           list.get(i).execute();
        }
    }
}