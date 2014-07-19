import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Forma extends JFrame {
    JTextField msg = null;
    JPanel panel = null;
    JLabel label = null;
    JTextArea textA = null;
    String userName;

    Forma(String user) {
        userName = user;
        JFrame frame = new JFrame("SkypeMINI");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);      // po centru forma
        frame.setResizable(false);  // knopka zavernut okno
        frame.setIconImage(new ImageIcon("C:\\Users\\abgulia\\Documents\\SWING\\src\\a.png").getImage());

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        label = new JLabel(" Skype user");
        label.setForeground(new Color(192, 227, 173));
        label.setFont(new Font("Serif", Font.BOLD, 17));

        msg = new JTextField();
        msg.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Date dNow = new Date();
                        SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss dd.MM.yyyy");

                        userName.toUpperCase();
                        textA.append("[" + ft.format(dNow) + " ] " + userName + " : " + "\n");
                        textA.append(msg.getText() + "\n");
                        msg.setText("\n");
                    }
                }
        );
        textA = new JTextArea(40, 0);
        JScrollPane scrol = new JScrollPane(textA);
        textA.setForeground(new Color(16, 25, 86));
        textA.setFont(new Font("Serif", Font.BOLD, 14));
        textA.setEditable(false);

        panel.add(label, BorderLayout.NORTH);  // na ponel podkljuchit knopku
        panel.add(scrol, BorderLayout.CENTER);
        panel.add(msg, BorderLayout.SOUTH);
        panel.setBackground(new Color(18, 165, 244));
      /*  for (int i = 0; i < 100; i++) {
            Thread.sleep(500);
            panel.setBackground(new Color(i+10,i*13,1*2));
        }
      */

        frame.add(panel);
        frame.setVisible(true);

    }


}
