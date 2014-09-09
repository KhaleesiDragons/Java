public class BuilderPattern {
    public static void main(String[] args) {
        Builder box1=new Box1(new Gift());
        Builder box2=new Box2(new Gift());

        Prodavac p=new Prodavac();
        p.setB(box1);
        p.getGift();
    }
}

class Gift{
    private String modelNotebook;
    private String modelMobile;
    private String modelEbook;

    void setModelNotebook(String modelNotebook) {
        this.modelNotebook = modelNotebook;
    }

    void setModelMobile(String modelMobile) {
        this.modelMobile = modelMobile;
    }

    void setModelEbook(String modelEbook) {
        this.modelEbook = modelEbook;
    }

    void getGift(){
        System.out.println(modelEbook);
        System.out.println(modelMobile);
        System.out.println(modelNotebook);
    }

}

interface Builder{
    Gift getGift();
}
class Box1 implements Builder{
   Gift s;

    Box1(Gift s) {
        this.s = s;
        s.setModelEbook("Nook");
        s.setModelMobile("Iphone");
        s.setModelNotebook("Lenovo");
    }

    public Gift getGift(){
        return s;
    }

}


class Box2 implements Builder{
    Gift s;

    Box2(Gift s) {
        this.s = s;
        s.setModelEbook("Nook1");
        s.setModelMobile("Iphone2");
        s.setModelNotebook("Lenovo3");
    }

    public Gift getGift(){
        return s;
    }

}

class Prodavac{
    private Builder b;

    void setB(Builder b) {
        this.b = b;

    }
    void getGift(){
       b.getGift().getGift();
    }

}
