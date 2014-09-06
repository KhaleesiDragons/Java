public class StrategyPattern {
    public static void main(String[] args) {
        Contexty c=new Contexty("It is Strategy :)");
        c.readText();

        System.out.println("change strategy");
        c.changeStrategy(new LOW());
        c.readText();
    }
}

interface Strategy    {
    String algorithm(String str);
}

class UPPER implements Strategy{
    @Override
    public String algorithm(String str) {
        return str.toUpperCase() ;
    }
}

class LOW implements Strategy{
    @Override
    public String algorithm(String str) {
        return str.toLowerCase();
    }
}

class Contexty{
   private Strategy strategy;
   private  String txt;
    Contexty(String text) {
        this.strategy = new UPPER();

        txt=strategy.algorithm(text);
    }

    void readText(){
        System.out.println(txt);
    }

    void changeStrategy(Strategy strategy){
           txt=strategy.algorithm(txt);
    }
}



