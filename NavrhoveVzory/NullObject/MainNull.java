package NullObject;

import java.util.ArrayList;

public class MainNull {
    public static void main(String[] args) {
        Seznam A=new ClassA();
        for (int i = 0; i < 3; i++) {
           A.addName("name"+i);
        }

        VypisSeznam vs=new  VypisSeznam();
        vs.vypis(A);



    }
}

class VypisSeznam  {
    void vypis(Seznam A){
        for (int i = 0; i < 3; i++) {
            System.out.println(A.getName(i));
        }
        Seznam nc=new NullClass();
        System.out.println(nc.getName(0));
    }
}

interface Seznam{
    String getName(int i);
    void addName(String name);
}

class ClassA implements Seznam{
    String name;
    ArrayList classA=new ArrayList();
    ClassA() {
    }



    @Override
    public String getName(int i) {
      return classA.get(i)+" ";
    }

    @Override
    public void addName(String name) {
        classA.add(name);
    }
}

class NullClass implements Seznam{

    @Override
    public String getName(int i) {
        return "konec";
    }

    @Override
    public void addName(String name) {

    }
}

