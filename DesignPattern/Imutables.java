import com.google.common.collect.ImmutableList;

public class Imutables {
    public static void main(String[] args) {
        ImmutableList<Integer> list = new ImmutableList.Builder<Integer>()
                .add(0)
                .add(1)
                .add(20)
                .add(30)
                .build();
        System.out.println(list.get(2));     // 20
        System.out.println(list.size());      //4
        System.out.println(list.subList(1,4).size());     //3
        System.out.println(list.toString());             //[0, 1, 20, 30]
        System.out.println(list.get(3).equals(30));         // true

        ImmutableList<Integer> ll=list.subList(1,4).asList();
        System.out.println(ll.size());
        System.out.println(ll.toString());
    }
}
