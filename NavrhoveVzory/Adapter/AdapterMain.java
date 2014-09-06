package Adapter;

/**
 * Created with IntelliJ IDEA.
 * User: abgulia
 * Date: 9.6.14
 * Time: 18:29
 * To change this template use File | Settings | File Templates.
 */
public class AdapterMain {
}

class Programming {
    public void code() {
        System.out.println("Just do it!");
    }

    public void debug() {
        System.out.println("");
    }
}

interface ModernProgramming {
    void ide();
}

class SurrogateAdapter implements ModernProgramming {
    Programming programming;

    public SurrogateAdapter(Programming wih) {
        programming = wih;
    }

    public void ide() {
        programming.code();
        programming.debug();
    }
}
