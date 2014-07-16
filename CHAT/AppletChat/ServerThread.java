package Chat;

import java.io.*;
import java.net.*;

/**
 *
 * @author gulja
 */
public class ServerThread extends Thread
{
//  Server, který vytvorel nás
private Server server;
//Socket připojen k klientovi.
private Socket socket;

// Constructor.
public ServerThread( Server server, Socket socket ) {
// Uložíme parametry
this.server = server;
this.socket = socket;
// spusteni thread
start();
}
// To běží v samostatném vlákně, 
//kdy je start () volána v konstruktoru.
public void run() {
try {
// Vytvoře DataInputStream pro komunikaci, 
    //který klient používá pro DataOutputStream, aby napsat nám
DataInputStream din = new DataInputStream( socket.getInputStream() );
// Znovu a znovu, a to navždy ...
while (true) {
// ... Přečte další zprávy...
String message = din.readUTF();
// ... říct světu ...
System.out.println( "Zasílání "+message );
// ... a serveru odeslat všem klientům
server.sendToAll( message );
}
} catch( EOFException ie ) {
} catch( IOException ie ) {
ie.printStackTrace();
} finally {
           
    //Spojení je ukončeno pro toho či onoho důvodu,
    //tak se server zabývá ho         
    server.removeConnection( socket );
                        
}
}
}
