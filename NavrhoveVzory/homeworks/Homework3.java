
import java.util.Stack;


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
        this.nextNode=n;

    }

    @Override
    public boolean hasNext() {
        return nextNode!=null;
    }

    @Override
    public int next() {
        int contents=nextNode.contents;
        if(nextNode.left!=null){
            nextNode=nextNode.left;
        } else if(nextNode.right!=null){
            nextNode=nextNode.right;
        } else{
            Node parent=nextNode.parent;
            Node child=nextNode;
            while(parent!=null && (parent.right==child || parent.right==null)){
                child=parent;
                parent=parent.parent;
            }
            if(parent==null){
                nextNode=null;
            } else{
                nextNode=parent.right;
            }

        }
        return contents;
    }

}


class InorderIterator implements CustomIterator {
    Node next;
   private  Stack<Node> st=new Stack();
   private  Stack<Node> stt=new Stack();
    
    public InorderIterator(Node node) {
        this.next=node;
    }

    @Override
    public boolean hasNext() {
       return next!=null;
    }

    @Override
    public int next() {
       
        if (stt.isEmpty()) {
                stt.add(null);
                InOrder(this.next);
               while(!st.empty()){
                stt.add(st.pop());
                        }
            }
        int cont=this.stt.pop().contents;
        this.next=stt.peek();
        return  cont;
   
    }
    
    private void InOrder(Node Root)
    {
        if( Root!= null ) {
	        InOrder( Root.left );
	         st.add(Root);
	        InOrder( Root.right );
	    }
    }
}


class PostorderIterator implements CustomIterator {
    private Node next;
 private  Stack<Node> st=new Stack();
 private Stack<Node> stt=new Stack(); 
    
    public PostorderIterator(Node node) {
       this.next=node;
    }

    @Override
    public boolean hasNext() {
        return next!=null;
    }
    
   
    @Override
    public int next() {
    
        if (stt.isEmpty()) {
                stt.add(null);
                postOrder(this.next);
               while(!st.empty()){
                stt.add(st.pop());
                        }
            }
        int cont=this.stt.pop().contents;
        this.next=stt.peek();
        return  cont;
     
    }
    
    
    
    private void postOrder(Node Root)
        {
        if(Root != null)
        {
            this.next=Root;
            postOrder(Root.left);
            postOrder(Root.right);
            st.add(Root);
        }
        
    }
   
    }

