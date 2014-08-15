package Prototype;



public class PrototypeDemo {

    private Message message;

    PrototypeDemo(Message message) {
        this.message = message;
    }

    Message makeMessage() {
        return this.message.makeCopy();
    }

    public static void main(String[] args) {
        Message prototype = new EmailMessage();
        PrototypeDemo demo = new PrototypeDemo(prototype);
        System.out.println("Message " + demo.makeMessage());
    }
}

abstract class Message {

    public Message makeCopy() {
        try {
            return this.getClass().newInstance();
        } catch (InstantiationException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        }
    }
}

class EmailMessage extends Message {

    @Override
    public String toString() {
        return "EmailMessage";
    }

}

