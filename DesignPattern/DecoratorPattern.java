public class DecoratorPattern {
    public static void main(String[] args) {
        Caj prepeare=new Cukr(new Milk(new Lemon(new Voda())));
        prepeare.addComponents();
    }
}

interface Components{
    void addComponents();
}



abstract class Caj implements Components{
    Components c;
    public Caj(Components c) {
    this.c=c;
    }

    public void addComponents() {

    }
}

class Cukr extends Caj{

    public Cukr(Components c) {
        super(c);
    }

    @Override
    public void addComponents() {
        System.out.print("Cukr ");
        c.addComponents();
    }
}

class Milk extends Caj{
    public Milk(Components c) {
        super(c);
    }

    @Override
    public void addComponents() {
        System.out.print("+ Milk ");
        c.addComponents();
    }
}

class Lemon extends Caj{
    public Lemon(Components c) {
        super(c);
    }

    @Override
    public void addComponents() {
        System.out.print("+ Lemon ");
        c.addComponents();
    }
}

class Voda implements Components{
    @Override
    public void addComponents() {
        System.out.print("+ VODA = ");
        System.out.println(" END ");
    }
}