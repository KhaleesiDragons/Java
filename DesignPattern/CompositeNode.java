import java.util.ArrayList;

public class CompositeNode {
    public static void main(String[] args) {
        Composite com1=new Composite();
        Node n1=new Leaf(15);
        Node n2=new Leaf(17);
        com1.addNode(n1);
        com1.addNode(n2);

        Composite com2=new Composite();
        Node n3=new Leaf(12);
        Node n4=new Leaf(3);
        com2.addNode(n3);
        com2.addNode(n4);

        Composite com3=new Composite();
        com3.addNode(com1);
        com3.addNode(com2);

        com3.getNum();
    }
}

interface Node{

    void getNum();
}

class Leaf implements Node{
    int num;

    Leaf(int num) {
        this.num = num;
    }

    @Override
    public void getNum() {
        System.out.println(num);
    }
}

class Composite implements Node{
    ArrayList<Node> node=new ArrayList<Node>();

    void addNode(Node n){
        node.add(n);
    }

    @Override
    public void getNum() {
        for (int i = 0; i < node.size(); i++) {
             node.get(i).getNum();
        }
    }
}
