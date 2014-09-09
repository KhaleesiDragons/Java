
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Iterator;

class Decorator implements Actuator {

    SequentiallyComposingActuator actuator;

    Iterator<Pair<Predicate<Movable>, Actuator>> iterat;

    public Decorator(SequentiallyComposingActuator actuator) {
        this.actuator = actuator;
        iterat = actuator.iterable.iterator();
    }

    @Override
    public void actuate(Movable movable) {
        if (!iterat.hasNext()) {
            return;
        }
        Pair<Predicate<Movable>, Actuator> pair = iterat.next();
        while (pair.first.apply(movable)) {
            Actuator ac = pair.second.accept(new HorizontalFlip());
            ac.actuate(movable);
        }
    }

    @Override
    public Actuator accept(ActuatorVisitor v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

class HorizontalFlip implements ActuatorVisitor {

    @Override
    public Actuator visitUp(Up actuator) {
        return new Up();

    }

    @Override
    public Actuator visitDown(Down actuator) {
        return new Down();
    }

    @Override
    public Actuator visitLeft(Left actuator) {
        return new Right();
    }

    @Override
    public Actuator visitRight(Right actuator) {
        return new Left();
    }

    @Override
    public Actuator visitParallellyComposingActuator(ParallellyComposingActuator actuator) {
        ImmutableList<Actuator> t = actuator.motors;
        ArrayList<Actuator> a = new ArrayList<Actuator>();
        for (int i = 0; i < t.size(); i++) {
            Actuator ac = t.get(i).accept(new HorizontalFlip());
            a.add(ac);
        }
        ImmutableList i = ImmutableList.copyOf(a);
        ParallellyComposingActuator c = new ParallellyComposingActuator(i);
        return c;

    }

    @Override
    public Actuator visitAmplifyingActuator(AmplifyingActuator actuator) {
        Actuator t = actuator.motor.accept(new HorizontalFlip());
        return new AmplifyingActuator(t, actuator.factor);
    }

    @Override
    public Actuator visitSequentiallyComposingActuator(SequentiallyComposingActuator actuator) {
        return new Decorator(actuator);

    }

}
