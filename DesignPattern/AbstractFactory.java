import javax.swing.*;
import java.awt.*;

public class AbstractFactory {
    public static void main(String[] args) {
      JFrame jf=new JFrame("HR system");
        jf.setSize(300,400);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jp=new JPanel();
        jp.setVisible(true);
        jf.add(jp);
        Factory mac=new MacFactory();
        mac.setBtn(jp);
        mac.setScreen(jp);

    }
}

interface Factory{
    Window setScreen(JPanel jp);
    Buttons setBtn(JPanel jp);
}

class MacFactory implements Factory{
    @Override
    public Window setScreen(JPanel jp) {
        Window mac=new MacScreen();
        mac.setWindow(jp);
        return mac;
    }


    @Override
    public Buttons setBtn(JPanel jp) {
        Buttons btn=new MacBtn();
        btn.visio(jp);
        return btn;
    }
}

class WindowsFactory implements Factory{
    @Override
    public Window setScreen(JPanel jp) {
        Window mac=new WinScreen();
        mac.setWindow(jp);
        return mac;
    }


    @Override
    public Buttons setBtn(JPanel jp) {
        Buttons btn=new WinBtn();
        btn.visio(jp);
        return btn;
    }
}

interface Buttons{
  void visio(JPanel jp);
}

class WinBtn implements Buttons{
    @Override
    public void visio(JPanel jp) {
        JButton btn=new JButton("Windows");
        btn.setVisible(true);
        btn.setForeground(new Color(9, 125, 128));
        btn.setBackground(new Color(128, 32, 42));
        jp.add(btn, BorderLayout.CENTER);
    }
}


class MacBtn implements Buttons{

    @Override
    public void visio(JPanel jp) {
        JButton btn=new JButton("Mac");
        btn.setVisible(true);
        btn.setForeground(new Color(242, 225, 46));
        btn.setBackground(new Color(214, 123, 161));
        jp.add(btn, BorderLayout.CENTER);
    }
}

interface Window{
    void setWindow(JPanel jp);
}

class WinScreen  implements Window{

    @Override
    public void setWindow(JPanel jp) {
        jp.setBackground(new Color(65, 211, 42, 142));
    }
}

class MacScreen  implements Window{

    @Override
    public void setWindow(JPanel jp) {
        jp.setBackground(new Color(19, 176, 208, 128));
    }
}

