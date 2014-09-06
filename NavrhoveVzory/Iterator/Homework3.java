package Iterator;

import java.util.ArrayList;
import java.util.Stack;

interface CustomIterator {
    boolean hasNext();

    int next();

    void vypisSudy();
}

class Node {
    final int contents;
    final Node left, right;
    Node parent;

    Node(int contents, Node left, Node right) {
        this.contents = contents;
        this.left = left;
        if (left != null) left.parent = this;
        this.right = right;
        if (right != null) right.parent = this;
    }

    CustomIterator preorderIterator() {
        return new PreorderIterator(this);
    }

    CustomIterator inorderIterator() {
        return new InorderIterator(this);
    }

    CustomIterator postorderIterator() {
        return new PostorderIterator(this);
    }
}

class PreorderIterator implements CustomIterator {
    Node nextNode;

    public PreorderIterator(Node n) {
        this.nextNode = n;

    }

    @Override
    public boolean hasNext() {
        return nextNode != null;
    }

    @Override
    public int next() {
        int contents = nextNode.contents;
        if (nextNode.left != null) {
            nextNode = nextNode.left;
        } else if (nextNode.right != null) {
            nextNode = nextNode.right;
        } else {
            Node parent = nextNode.parent;
            Node child = nextNode;
            while (parent != null && (parent.right == child || parent.right == null)) {
                child = parent;
                parent = parent.parent;
            }
            if (parent == null) {
                nextNode = null;
            } else {
                nextNode = parent.right;
            }

        }
        return contents;
    }

    @Override
    public void vypisSudy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}


class InorderIterator implements CustomIterator {
    Node next;
    private Stack<Node> st = new Stack();
    private Stack<Node> stt = new Stack();

    private ArrayList alSudy=new ArrayList();

    public InorderIterator(Node node) {
        this.next = node;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public int next() {

        if (stt.isEmpty()) {
            stt.add(null);
            InOrder(this.next);
            while (!st.empty()) {
                stt.add(st.pop());
            }
        }
        int cont = this.stt.pop().contents;
        this.next = stt.peek();
          // pokud cislo je suda - ulozi do arraylistu
        if (isSudy(cont)) alSudy.add(cont);
        return cont;
    }


    public void vypisSudy(){
        System.out.println();
        for (int i = 0; i < alSudy.size(); i++) {
            System.out.print(alSudy.get(i)+" ");
        }
    }



    boolean isSudy(int cont) {
        if (cont % 2 == 0) return true;
        return false;
    }

    private void InOrder(Node Root) {
        if (Root != null) {
            InOrder(Root.left);
            st.add(Root);
            InOrder(Root.right);
        }
    }
}


class PostorderIterator implements CustomIterator {
    private Node next;
    private Stack<Node> st = new Stack();
    private Stack<Node> stt = new Stack();

    public PostorderIterator(Node node) {
        this.next = node;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }


    @Override
    public int next() {

        if (stt.isEmpty()) {
            stt.add(null);
            postOrder(this.next);
            while (!st.empty()) {
                stt.add(st.pop());
            }
        }
        int cont = this.stt.pop().contents;
        this.next = stt.peek();
        return cont;

    }

    @Override
    public void vypisSudy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    private void postOrder(Node Root) {
        if (Root != null) {
            this.next = Root;
            postOrder(Root.left);
            postOrder(Root.right);
            st.add(Root);
        }

    }

}


public class Homework3 {


    public static void main(String[] args) {
        Node n8 = new Node(8, null, null);
        Node n2 = new Node(2, null, n8);
        Node n11 = new Node(11, null, null);
        Node n5 = new Node(5, n11, n2);
        Node n7 = new Node(7, null, null);
        Node n6 = new Node(6, null, null);
        Node n4 = new Node(4, n6, n7);
        Node n3 = new Node(3, null, null);
        Node n12 = new Node(12, n3, n4);
        Node first = new Node(10, n12, n5);

        CustomIterator ci = first.inorderIterator();
        while (ci.hasNext()) {
            System.out.print(ci.next() + " ");
        }
        ci.vypisSudy();

    }
}
