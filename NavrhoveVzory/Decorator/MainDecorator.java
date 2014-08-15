package Decorator;


public class MainDecorator {
    public static void main(String[] args) {
        Countable cappuccino = new Integer(new Double(new Book()));
        System.out.println(cappuccino.getName().trim() + ": $" + cappuccino.getCount());
        Countable cafeMocha = new Integer(new Decimal(new Negative(new Positive(new Float(
                new Book())))));
        System.out.println(cafeMocha.getName().trim() + ": $" + cafeMocha.getCount());
    }
}

interface Countable {
    String getName();

    float getCount();
}

class Book implements Countable {
    public String getName() {
        return "Book";
    }

    public float getCount() {
        return 0;
    }
}

abstract class Number implements Countable {
    protected Countable component;

    Number(Countable component) {
        this.component = component;
    }

    public float getCount() {
        return component.getCount();
    }

    public abstract String getName();
}

class Integer extends Number {
    private float value = 0.75f;

    private String description = " integer";

    public Integer(Countable component) {
        super(component);
    }

    public float getCount() {
        return component.getCount() + value;
    }

    public String getName() {
        return component.getName() + description;
    }
}

class Float extends Number {

    private String description = " float";

    public Float(Countable component) {
        super(component);
    }

    public String getName() {
        return component.getName() + description;

    }
}

class Double extends Number {
    private float value = 0.25f;

    private String description = " double";

    public Double(Countable component) {
        super(component);
    }

    public float getCount() {
        return component.getCount() + value;
    }

    public String getName() {
        return component.getName() + description;
    }
}

class Decimal extends Number {
    private float value = 0.25f;

    private String description = " decimal";

    public Decimal(Countable component) {
        super(component);
    }

    public float getCount() {
        return component.getCount() + value;
    }

    public String getName() {
        return component.getName() + description;
    }
}

class Positive extends Number {
    private float cost = 0.25f;

    private String description = " positive";

    public Positive(Countable component) {
        super(component);
    }

    public float getCount() {
        return component.getCount() + cost;
    }

    public String getName() {
        return component.getName() + description;
    }
}

class Negative extends Number {

    private float value = -0.25f;

    private String description = " negative";

    public Negative(Countable component) {
        super(component);
    }

    public float getCount() {
        return component.getCount() + value;
    }

    public String getName() {
        return component.getName() + description;
    }

}
