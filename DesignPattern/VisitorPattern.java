public class VisitorPattern {
    public static void main(String[] args) {
        Goods m=new Mobile();
        m.price(1000);
        m.name("Iphone");
        System.out.println(m.getGoods());
        m.accept(new Astana());
        System.out.println(m.getGoods());

        Goods notebook=new Notebook();
        notebook.price(15000);
        notebook.name("Lenovo");
        System.out.println(notebook.getGoods());
        notebook.accept(new Astana());
        System.out.println(notebook.getGoods());
    }
}

interface Goods{
     void name(String name);
    void price(int p);
    String getGoods();
    void accept(Visitor v);
}

interface Visitor{
    void visit(Mobile g);
    void visit(Notebook g);
}

class  Astana implements Visitor{
    @Override
    public void visit(Mobile g) {
        g.price(20000);
        g.setCountry("America");

    }

    @Override
    public void visit(Notebook g) {
        g.name(" Lenovo ");
        g.price(21000);
    }
}



class Notebook implements Goods{
    private String nameGood;
    private int price;
    @Override
    public void name(String name) {
        this.nameGood=name;
    }

    @Override
    public void price(int p) {
        this.price=p;
    }

    @Override
    public String getGoods() {
        return nameGood+" "+this.price;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}



class Mobile implements Goods{
    private String nameGood;
    private int price;
    private String fromCountry;
    @Override
    public void name(String name) {
        this.nameGood=name;
    }

    @Override
    public void price(int p) {
        this.price=p;
    }

    void setCountry(String c){
        this.fromCountry=c;
    }
    @Override
    public String getGoods() {
        return nameGood+" "+this.price;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}