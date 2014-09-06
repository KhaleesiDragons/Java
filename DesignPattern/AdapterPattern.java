public class AdapterPattern {
    public static void main(String[] args) {
       Target t=new Adapter(new Girl());
       t.jmeno();
       t.mesto();
       t.plat();
       t.vyska();
    }
}

class Girl{
    boolean isTall(){
        return true;
    }

    int Paymount(){
        return 20000;
    }

    String name(){
        return "Alice";
    }

    String city(){
        return "New York";
    }
}

interface Target{
  void mesto();
  void vyska();
  void plat();
  void jmeno();
}

class Adapter implements Target{
    Girl g;

    Adapter(Girl g) {
        this.g = g;
    }

    @Override
    public void mesto() {
        System.out.println("Jake mesto? " + g.city());
    }

    @Override
    public void vyska() {
        System.out.println("Je vysoka? "+g.isTall());
    }

    @Override
    public void plat() {
        System.out.println("Jaky plat? "+g.Paymount());
    }

    @Override
    public void jmeno() {
        System.out.println("Moje jmeno: "+g.name());
    }
}