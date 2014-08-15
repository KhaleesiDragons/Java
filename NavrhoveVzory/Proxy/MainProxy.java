package Proxy;

public class MainProxy {
    public static void main(String[] args) {
    ProxyBase p = new Proxy();
    p.taskOne();
    p.taskTwo();
    p.taskThree();
}
}

interface ProxyBase {
    void taskOne();

    void taskTwo();

    void taskThree();
}
class Implementation implements ProxyBase {
    public void taskOne() {
        System.out.println("Implementation.f()");
    }
    public void taskTwo() {
        System.out.println("Implementation.g()");
    }
    public void taskThree() {
        System.out.println("Implementation.h()");
    }
}
class Proxy implements ProxyBase {
    private ProxyBase implementation;

    public Proxy() {
        implementation = new Implementation();
    }

    public void taskOne() {
        implementation.taskOne();
    }

    public void taskTwo() {
        implementation.taskTwo();
    }

    public void taskThree() {
        implementation.taskThree();
    }
}