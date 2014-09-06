import java.util.ArrayList;
import java.util.List;

public class ObserverPattern {
    public static void main(String[] args) {
        String maximNews="MAXIM MAXIM MAXIM";

        Observer lena=new Observers("Lena");
        Observer jana=new Observers("Jana");
        Subject maxim=new Magazin();
        maxim.addObserver(lena);
        maxim.addObserver(jana);
        maxim.notifyObserver(maximNews);

        Observer sam=new Observers("Sam");
        Observer jim=new Observers("Jim");
        Subject helth=new Magazin();
        String helthNews="news for ledies";
        helth.addObserver(sam);
        helth.addObserver(jim);
        helth.notifyObserver(helthNews);
    }
}

interface Subject{
    void addObserver(Observer o);
    void removeObserver();
    void notifyObserver(String zprava);

}

class Magazin implements Subject{
     List<Observer> observer=new ArrayList<Observer>();
    List<String> novinky=new ArrayList<String>();


    @Override
    public void addObserver(Observer o) {
        observer.add(o);
    }

    @Override
    public void removeObserver() {
       observer.remove(0);
    }

    @Override
    public void notifyObserver(String zprava) {

        for (int i = 0; i < observer.size(); i++) {
           observer.get(i).update(zprava);
        }
    }

}


interface Observer {
    void update(String news);
}

class Observers implements Observer{

    String name;
    Observers(String name) {
        this.name=name;
    }

    public void update(String news) {
        System.out.println(name+":"+news);
        }

}
