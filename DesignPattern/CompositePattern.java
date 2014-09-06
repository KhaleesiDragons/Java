import java.util.ArrayList;
import java.util.List;

public class CompositePattern {
    public static void main(String[] args) {
        Manager m=new Manager();
        m.addMembers(new Worker(50));
        m.addMembers(new Worker(20));
        m.addMembers(new Worker(30));
        m.addMembers(new Worker(40));
        Manager m1=new Manager();
        m1.addMembers(new Worker(20));
        m1.addMembers(new Worker(10));
        m1.addMembers(new Worker(10));
        m.addMembers(m1);
        m.SayHours();
    }
}

interface IEmploee{
    void SayHours();
}

class Worker implements IEmploee{
    int hours;
    Worker(int h) {
        this.hours=h;
    }

    public void SayHours(){
        System.out.println(hours);
    }
}

class Manager implements IEmploee{
    List<IEmploee> mambers=new ArrayList<IEmploee>();
     void addMembers(IEmploee ie){
        mambers.add(ie) ;
     }


   public void SayHours(){
       System.out.println(" Hours of employee");
       for (int i = 0; i <mambers.size(); i++) {
           mambers.get(i).SayHours();
       }
    }

}