interface Expr extends Visitable {

}

class True implements Expr {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class False implements Expr {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class Var implements Expr {
    String name;

    public Var(String name) {
        this.name = name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

abstract class BinExpr implements Expr {
    Expr leftOp;
    Expr rightOp;

    public BinExpr(Expr leftOp, Expr rightOp) {
        this.leftOp = leftOp;
        this.rightOp = rightOp;
    }
}

abstract class UnExpr implements Expr {
    Expr op;

    public UnExpr(Expr op) {
        this.op = op;
    }
}

class And extends BinExpr {
    public And(Expr leftOp, Expr rightOp) {
        super(leftOp, rightOp);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class Or extends BinExpr {
    public Or(Expr leftOp, Expr rightOp) {
        super(leftOp, rightOp);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class Xor extends BinExpr {
    public Xor(Expr leftOp, Expr rightOp) {
        super(leftOp, rightOp);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class Neg extends UnExpr {
    public Neg(Expr op) {
        super(op);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

abstract class Visitor {
    public abstract void visit(True trueExpr);
    public abstract void visit(False falseExpr);
    public abstract void visit(Var varExpr);
    public abstract void visit(And andExpr);
    public abstract void visit(Or orExpr);
    public abstract void visit(Xor xorExpr);
    public abstract void visit(Neg negExpr);
}

interface Visitable {
    public void accept(Visitor visitor);
}

class Dumper extends Visitor {

    public void dump(Expr expr) {
        expr.accept(this);
    }

    @Override
    public void visit(True trueExpr) {
        System.out.print("TRUE");
    }

    @Override
    public void visit(False falseExpr) {
        System.out.print("FALSE");
    }

    @Override
    public void visit(Var varExpr) {
        System.out.print(varExpr.name);
    }

    @Override
    public void visit(And andExpr) {
        System.out.print("(");
        andExpr.leftOp.accept(this);
        System.out.print(" & ");
        andExpr.rightOp.accept(this);
        System.out.print(")");
    }

    @Override
    public void visit(Or orExpr) {
        System.out.print("(");
        orExpr.leftOp.accept(this);
        System.out.print(" | ");
        orExpr.rightOp.accept(this);
        System.out.print(")");
    }

    @Override
    public void visit(Xor xorExpr) {
        System.out.print("(");
        xorExpr.leftOp.accept(this);
        System.out.print(" # ");
        xorExpr.rightOp.accept(this);
        System.out.print(")");
    }

    @Override
    public void visit(Neg negExpr) {
        System.out.print("!(");
        negExpr.op.accept(this);
        System.out.print(")");
    }
}

public class Main {
    public static void main(String ... args) {
        Expr expression = new Xor(new Or(new And(new Var("X"), new True()), new False()), new Neg(new True()));
        Dumper dumper = new Dumper();
        dumper.dump(expression);
    }
}
