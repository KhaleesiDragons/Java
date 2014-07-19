import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    JFrame frame = null;
    JTextField textField1;
    JPasswordField passwordField;
    JLabel label1, label2, label3, label4;
    JPanel panel;

    boolean isPasswordCorrect(char[] inputPassword) {
        char[] actualPassword = {'p', 'a', 's', 's'};
        if (inputPassword.length != actualPassword.length)
            return false; // Return false if lengths are unequal
        for (int i = 0; i < inputPassword.length; i++)
            if (inputPassword[i] != actualPassword[i])
                return false;
        return true;
    }

    public MainFrame() throws Exception {
        frame = new JFrame("SkypeMINI");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("C:\\Users\\abgulia\\Documents\\SWING\\src\\a.png").getImage());

        panel = new JPanel(new GridLayout(7, 7));
        panel.setBackground(new Color(18, 165, 244));
        label1 = new JLabel("Login: ");
        label2 = new JLabel("Password: ");
        label3 = new JLabel("Sign Up: ");
        label3.setFont(new Font("Serif", Font.PLAIN, 15));
        label3.setForeground(new Color(255,238,0));

        label4 = new JLabel("");
        label4.setFont(new Font("Serif", Font.BOLD, 14));
        label4.setForeground(new Color(184,66,80));


        textField1 = new JTextField();
        passwordField = new JPasswordField(8);
        label1.setLabelFor(textField1);
        label2.setLabelFor(passwordField);

        JButton btn = new JButton("Log in");
        btn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {

                        String log = textField1.getText();
                        char[] pas = passwordField.getPassword();


                        if (log.equals("user") == true && isPasswordCorrect(pas) == true) {
                            Forma forma = new Forma(log);

                        } else {//JOptionPane.showMessageDialog(frame, "This login or password is not exist");
                            label4.setText("This login or password is not exist");
                        }
                    }
                }

        );

        panel.add(label1);
        panel.add(textField1);
        panel.add(label2);
        panel.add(passwordField);
        panel.add(label3);
        panel.add(label4);
        frame.add(panel);
        frame.add(btn, BorderLayout.SOUTH);
        frame.setSize(250, 200);
        frame.setVisible(true);
    }


    public static void main(String[] args) throws Exception {
        MainFrame mf = new MainFrame();
    }
}
