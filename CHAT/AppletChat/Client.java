package Chat;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gulja
 */

public class Client extends Panel implements Runnable
{
// Komponenty pro vizuální zobrazení okna chatu
private TextField tf = new TextField();
private TextArea ta = new TextArea();
private Button bt=new Button("close the chat");
// socket nás spojuje se serverem
private Socket socket;
// Proudy, komunikovat se serverem, ty pocházejí ze socketu
private DataOutputStream dout;
private DataInputStream din;
private String str;
private String host;

// Constructor

public Client(String str, String host,int port ) {
this.str=str;

// Nastavení uzivatelskeho rozhrani
setLayout( new BorderLayout() );
add( "North", tf );
add( "Center", ta );
add("South",bt);
bt.setBackground(Color.white);
ta.setBackground(Color.white);
ta.setEditable( false );
bt.addActionListener(new ActionListener(){
  public void actionPerformed(ActionEvent e){
  System.exit(-1);
                try {
                    socket.close();
                    dout.close();
                    din.close();
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
  }});
tf.addActionListener( new ActionListener() {
public void actionPerformed( ActionEvent e ) {
processMessage( e.getActionCommand() );
}
} );
// Připojení k serveru
try {
//Iniciovat spojení
socket = new Socket( host, port );
//Dostali jsme spojení! YES
System.out.println( "připojení k "+socket );
//Pojďme chytit potoky a vytvářet vstupni / výstupní proudy z nich
din = new DataInputStream( socket.getInputStream() );
dout = new DataOutputStream( socket.getOutputStream() );
// Začněte na pozadí Thead pro příjem zpráv
Thread th=new Thread( this );
th.start();
} catch( IOException ie ) { System.out.println( ie ); }
}
// Volána metoda, když uživatel zadá něco
private void processMessage( String message ) {
FileWriter fw = null;
try {
// Odeslat na server
    dout.writeUTF( str+": "+message );
    Date d = new Date();
    DateFormat df = new SimpleDateFormat("HH:mm:ss yyyy/MM/dd");

// Ulozit zpravy do souboru
    fw = new FileWriter("History",true);  
    fw.append(df.format(d)+" "+str+": "+message +"\n");   
    fw.close();
// Ocistit pole pro psaní textu
    tf.setText( "" );
} 
catch( IOException ie ) { System.out.println( ie ); 
}
}
// Pozadí tohoto vlákna běží: Zobrazi příspěvky od ostatních oken
public void run() {

   try {
   // Přijímat zprávy jeden po druhém, a to navždy
   while (true) {
   // Získejte další zprávy
   String message = din.readUTF();
   //Tisk v textarea
   ta.append(message+"\n" ); 
   }    
   } catch( IOException ie ) { System.out.println( ie );   
   }
   
}

}
