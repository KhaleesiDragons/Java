package exam;

public class Ukol_1 {
    public static void main(String[] args) {
        Var var=new Var("X");
        True TRUE=new True();
        False FALSE=new False();
        NEG negTRUE=new NEG(TRUE);

        AND_Expr and_expr=new AND_Expr(var,TRUE);
        OR_Expr or_expr=new OR_Expr(and_expr, FALSE);
        XOR_Expr xor_expr=new XOR_Expr(or_expr, negTRUE);
    }
}



interface Expr {
}

class True implements Expr {
}

class False implements Expr {
}

class Var implements Expr {

    String name;

    public Var(String name) {
        this.name = name;
    }
}

interface Visitable{
    Expr accept(Visitor v);
}
abstract class BinExpr implements Expr {

    Expr leftOp;
    Expr rightOp;

    public BinExpr(Expr leftOp, Expr rightOp) {
        this.leftOp = leftOp;
        this.rightOp = rightOp;
    }
}


class AND_Expr extends BinExpr implements Visitable{

    public  AND_Expr(Expr leftOp, Expr rightOp) {
        super(leftOp, rightOp);
    }

    @Override
    public Expr accept(Visitor v) {
        return v.visit(this);
    }
}


class OR_Expr extends BinExpr implements Visitable{

    public  OR_Expr(Expr leftOp, Expr rightOp) {
        super(leftOp, rightOp);
    }

    @Override
    public Expr accept(Visitor v) {
        return v.visit(this);
    }
}

class XOR_Expr extends BinExpr implements Visitable{

    public  XOR_Expr(Expr leftOp, Expr rightOp) {
        super(leftOp, rightOp);
    }

    @Override
    public Expr accept(Visitor v) {
        return v.visit(this);
    }
}

abstract class UnExpr implements Expr {

    Expr op;

    public UnExpr(Expr op) {
        this.op = op;
    }
}

class NEG extends UnExpr{

    public NEG(Expr op) {
        super(op);
    }
}

interface Visitor{
    Expr visit(AND_Expr and_expr);
    Expr visit(OR_Expr or_expr);
    Expr visit(XOR_Expr xor_expr);
}

class VisiteVyraz{

}