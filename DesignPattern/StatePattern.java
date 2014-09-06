public class StatePattern {
    public static void main(String[] args) {
        Context conUp=new Context(new Upcase());
        conUp.Request("HALLO-HALLO");

        Context conLow=new Context(new Lowcase());
        conLow.Request("HALLO-HALLO");
    }
}

interface State{
    String handle(String text);
}

class Upcase implements State{

    @Override
    public String handle(String text) {
      return text.toUpperCase();
    }
}


class Lowcase implements State{

    @Override
    public String handle(String text) {
        return text.toLowerCase();
    }
}

class Context{
    State s;

    Context(State s) {
        this.s = s;
    }

    void Request(String txt){
        System.out.println(s.handle(txt));
    }
}