class Homework1 {
   private int count1 = 0;

    static int count2=0;

    boolean f() {
        return true;
    }

    static boolean g() {
        return false;
    }

    int h() {
      return  ++this.count1;
    }

    int i() {
        return ++count2;
    }    
}
