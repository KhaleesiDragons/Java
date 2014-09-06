package Stategy;

public class MainStrategy {
    public static void main(String[] args) {
        Service service1 = new Service("1 HELLO");
        Service service2 = new Service("2 hello");
        service1.changeState(new DownClass());
        service2.changeState(new UpClass());
        service1.service1();
        service2.service1();

    }
}

interface State {
    void operation(String str);
}

class UpClass implements State {
    @Override
    public void operation(String str) {
        System.out.println(str.toUpperCase());
    }
}

class DownClass implements State {
    @Override
    public void operation(String str) {
        System.out.println(str.toLowerCase());
    }
}

class Service {
    private State state;
    private String str;

    Service(String str) {
        this.state = new UpClass();
        this.str = str;
    }

    void service1() {
        state.operation(this.str);
    }

    void changeState(State state){
        this.state=state;
    }
}


