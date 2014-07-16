
package Chat;

import java.applet.Applet;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;

/**
 *
 * @author gulja
 */
public class ClientApplet extends Applet
 {
    public void init() {
int port = 5555;
String str=JOptionPane.showInputDialog(null,
                "Zadej Login",
                "CHAT",
                JOptionPane.QUESTION_MESSAGE);
String host = "localhost";      
Client client=new Client(str,host,port);
BorderLayout bl=new BorderLayout(); 
setLayout(bl);
add( "Center", client );
     
}

   

}
