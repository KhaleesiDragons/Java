
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


class Homework5 {
  static int index = 0;
    static Actuator getFirstActuator() {
        Up up = new Up();
        Right right = new Right();
        ImmutableList<Actuator> motors;
        ImmutableList.Builder<Actuator> builder = ImmutableList.builder();
      
        builder.add(up);
        builder.add(right);
        motors = builder.build();
        return new ParallellyComposingActuator(motors);
    }

    static Actuator getSecondActuator() {
        boolean kL = Keyboard.leftArrowPressedDown();
        boolean kR = Keyboard.rightArrowPressedDown();
        ImmutableList<Actuator> list = new ImmutableList.Builder<Actuator>().add(new Right()).add(new Left()).build();
        Actuator res = new ParallellyComposingActuator(list);
        if (kL == true && kR == false) {
            return new Left();
        }
        if (kL == false && kR == true) {
            return new Right();
        }
        return res;
    }

    static Actuator getThirdActuator(final Actuator firstActuator, Actuator secondActuator) {
      
        ArrayList<Actuator> ar = new ArrayList<>();
        ar.add(firstActuator);
        ar.add(secondActuator);
        return new ThirdActuator(ar);

    }
}
class ThirdActuator implements Actuator {

    ArrayList<Actuator> ar;
    int index = 0;
    int pocet1 = 1;
    int pocet2 = 1;

    public ThirdActuator(ArrayList<Actuator> iterable) {
        this.ar = iterable;
    }

    @Override
    public void actuate(Movable movable) {
        int ind = index % 2;
        if (pocet1 == 10) {
            pocet1 = 0;
            index++;
        } else {
            if (pocet2 == 20) {
                pocet2 = 0;
                index++;
            }
        }
        ar.get(ind).actuate(movable);
        if(ind == 0){
            pocet1++;
        }
        else{
            pocet2++;
        }
    }   
}

class Down implements Actuator {

    @Override
    public void actuate(Movable movable) {
        movable.move(0, 1);
    }
}

class Left implements Actuator {

    @Override
    public void actuate(Movable movable) {
        movable.move(-1, 0);
    }
}

class Right implements Actuator {

    @Override
    public void actuate(Movable movable) {

        movable.move(1, 0);
    }
}

class Up implements Actuator {
	@Override
    public void actuate(Movable movable) {

        movable.move(0, -1);
    }
}

class ParallellyComposingActuator implements Actuator {

    ImmutableList<Actuator> motors;

    public ParallellyComposingActuator(ImmutableList<Actuator> motors) {
        this.motors = motors;
    }

    @Override
    public void actuate(Movable movable) {
        Iterator it = motors.iterator();
        while (it.hasNext()) {
            Actuator a = (Actuator) it.next();
            a.actuate(movable);

        }
    }
}

class AmplifyingActuator implements Actuator {

    private int factor;
    private Actuator motor;

    public AmplifyingActuator(Actuator motor, int factor) {
        this.factor = factor;
        this.motor = motor;
    }

    @Override
    public void actuate(Movable movable) {
        if (motor == null) {
            return;
        }
        for (int i = 0; i < this.factor; i++) {
            motor.actuate(movable);
        }
    }
}

 class SequentiallyComposingActuator implements Actuator {

    Iterator<Pair<Predicate<Movable>, Actuator>> it;

    public SequentiallyComposingActuator(Iterable<Pair<Predicate<Movable>, Actuator>> iterable) {
        this.it = iterable.iterator();
    }

    @Override
    public void actuate(Movable movable) {
        if (it.hasNext()) {
            Pair<Predicate<Movable>, Actuator> para = it.next();
            while (para.first.apply(movable)) {
                if (para.second == null) {
                    break;
                }
                para.second.actuate(movable);
            }
        }
    }
}
