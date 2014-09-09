
import java.util.HashSet;
import java.util.Set;

interface OMOSetView {
    public boolean contains(int element);

    public  int[] toArray();

    public OMOSetView copy();
}

class OMOSet implements OMOSetView {
    Set<Integer> set = new HashSet<Integer>();

    public void add(int element) {
        set.add(element);
    }

    public void remove(int element) {
        set.remove(element);
    }


    @Override
    public boolean contains(int element) {
        return set.contains(element);
    }

    @Override
    public int[] toArray() {
        int[] pole = new int[set.size()];
        int count = 0;
        for (Integer i : set) {
            pole[count] = i;
            count++;
        }
        return pole;
    }

    @Override
    public OMOSetView copy() {
        OMOSet om = new OMOSet();
        for (int a:set) {
            om.add(a);
        }
        return om;
    }
}

class OMOSetUnion implements OMOSetView {
    private OMOSetView setA;
    private  OMOSetView setB;


    OMOSetUnion(OMOSetView setA, OMOSetView setB) {
        this.setA=setA;
        this.setB=setB;
    }

    @Override
    public boolean contains(int element){
        return setA.contains(element) || setB.contains(element);
    }
    @Override
    public  int[] toArray(){
        OMOSet om=new OMOSet();
        int[] poleAll = new int[setA.toArray().length + setB.toArray().length];

        System.arraycopy(setA.toArray(), 0, poleAll, 0, setA.toArray().length);
        System.arraycopy(setB.toArray(), 0, poleAll, setA.toArray().length, setB.toArray().length);

        for (int i = 0; i < poleAll.length; i++) {
            om.add(poleAll[i]);
        }
        return om.toArray();
    }
    @Override
    public OMOSetView copy(){
        OMOSetUnion union=new OMOSetUnion(setA.copy(),setB.copy());
        return union;
    }
}

class OMOSetIntersection implements OMOSetView {
    OMOSetView setA;
    OMOSetView setB;

    OMOSetIntersection(OMOSetView setA, OMOSetView setB) {
        this.setA=setA;
        this.setB=setB;
    }

    @Override
    public boolean contains(int element){
        return setA.contains(element) && setB.contains(element);
    }
    @Override
    public  int[] toArray(){
        OMOSet om=new OMOSet();
        int[] poleA=setA.toArray();
        for (int i = 0; i < poleA.length; i++) {
            int a=poleA[i];
            if (contains(a)) om.add(a);

        }
        return om.toArray();
    }
    @Override
    public OMOSetView copy(){
        OMOSetIntersection  Intersection=new OMOSetIntersection(this.setA.copy(), this.setB.copy());
        return Intersection;
    }
}

class OMOSetEven implements OMOSetView {
    OMOSetView setA;
    OMOSetEven(OMOSetView setA) {
        this.setA=setA;
    }
    @Override
    public boolean contains(int element){
        return (element % 2 == 0) && setA.contains(element);
    }
    @Override
    public  int[] toArray(){
        int[] poleA=setA.toArray();
        OMOSet os=new OMOSet();
        int count=0;
        for (int i = 0; i <poleA.length; i++) {
            if (poleA[i]%2==0)  {
                os.add(poleA[i]);
                count=count+1;
            }
        }
        return os.toArray();
    }
    @Override
    public OMOSetView copy(){
        return new OMOSetEven(this.setA.copy());
    }

}

class OMOSetComplement implements OMOSetView {
    OMOSetView setA;
    OMOSetView setB;

    OMOSetComplement(OMOSetView setA, OMOSetView setB) {
        this.setA=setA;
        this.setB=setB;
    }
    @Override
    public boolean contains(int element){

        return setA.contains(element) && !(setB.contains(element));
    }
    @Override
    public  int[] toArray(){
        OMOSet os=new OMOSet();
        int[] poleA=setA.toArray();
        for (int i = 0; i < poleA.length; i++) {
            int a=poleA[i];
            if (!setB.contains(a)) os.add(a);
        }
        return os.toArray();
    }
    @Override
    public OMOSetView copy(){
        return new OMOSetComplement(setA.copy(),setB.copy());
    }
}