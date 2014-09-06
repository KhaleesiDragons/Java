import java.util.ArrayList;
import java.util.List;

public class IteratorPattern {
    public static void main(String[] args) {
        List<Integer> ar=new ArrayList<Integer>();
        ar.add(2);
        ar.add(3);
        ar.add(9);
        ar.add(10);
        ar.add(12);
        Agregate seznam=new Seznam(ar);
        Iterator it=seznam.createIterator();
        while (it.hasnext()!=true){
            System.out.println(it.next());
        }
    }
}

interface Agregate{
   Iterator createIterator();
}



class Seznam implements Agregate{

    List<Integer> l;

    Seznam(List list) {
        this.l = list;
    }

    @Override
    public Iterator createIterator() {
        //To change body of implemented methods use File | Settings | File Templates.
        return new PreOrder(l);
    }
}

interface Iterator{
    boolean hasnext();
    int next();
}

class PreOrder implements Iterator{

    List<Integer> l;
    boolean next=false;

    PreOrder(List list) {
        this.l = list;
    }

    @Override
    public boolean hasnext() {
        return next;
    }

    @Override
    public int next() {

       int c=(int)l.get(0);
       l.remove(0);
        if (l.size()==0) next=true;
       return c;
    }
}
