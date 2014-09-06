public class ProxyPattern {
    public static void main(String[] args) {
        Vstup v=new Proxy("ahoj");
        v.task();
    }

}

interface Vstup{
    void task();
}

class Proxy implements Vstup{
    String text;
    Proxy(String txt) {
        this.text=txt;
    }

    @Override

    public void task() {
        if (text.equals("ahoj"))
           new RealAnswer().task();
        else System.out.println(" HAHA ");
    }
}

class RealAnswer implements Vstup{
    @Override
    public void task() {
        System.out.println("HI, you are win!!!");
    }
}
