package Chat;

import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author gulja
 */

public class Server
{
// ServerSocket budeme používat pro přijímání nových spojení
private ServerSocket ss;
// Mapování od soketů k DataOutputStreams. 
//To nám pomůže vyhnout se nutnosti vytvořit DataOutputStream 
//pokaždé, když chceme zápsat do potoku.
private Hashtable outputStreams = new Hashtable();
//Konstruktor a při-smyčka přijímá vše v jednom.
public Server( int port ) throws IOException {
// Vše, co musí udělat, je naslouchat
listen( port );
}
private void listen( int port ) throws IOException {
// Vytvare ServerSocket
ss = new ServerSocket( port );
// jsme připraveni 
System.out.println( "posloucham na "+ss );
//Mějte přijímat připojení navždy.
while (true) {
// Chyť další příchozí spojení
Socket s = ss.accept();
// Řekni světu máme ho
System.out.println( "spojení z "+s );
// Vytvořte DataOutputStream pro zápis dat na druhou stranu
DataOutputStream dout = new DataOutputStream( s.getOutputStream() );
// Uložit tento proud, takže nepotřebujeme vytvaret se znovu
outputStreams.put( s, dout );
// Vytvořit nové vlákno pro toto připojení, a pak zapomenout 
new ServerThread( this, s );
}
}
// Získejte výčet všech OutputStreams, jeden pro každý klient , ktere se pripojili k nam
Enumeration getOutputStreams() {
return outputStreams.elements();
}
// Send a message to all clients (utility routine)
void sendToAll( String message ) {
// We synchronize on this because another thread might be
// calling removeConnection() and this would screw us up
// as we tried to walk through the list
synchronized( outputStreams ) {
// Poslat zprávu pro všechny klienty ...
for (Enumeration e = getOutputStreams(); e.hasMoreElements(); ) {


// ... získat výstupní proud. ...
DataOutputStream dout = (DataOutputStream)e.nextElement();
// ... a zprávu odešlete
try {
dout.writeUTF( message );

} catch( IOException ie ) { System.out.println( ie ); }
}
}
}
//Odstraňte socket, a to je odpovídající výstupní proud, z našeho seznamu. 
//To je obvykle nazýván přípojný thread, který objevil, 
//že připojení ke klientovi je mrtev.
void removeConnection( Socket s ) {
// Synchronizaci, takže nemáme zkazit sendToAll (), 
//zatímco to jde dolů Seznam všech výstupních streamu.
synchronized( outputStreams ) {
try {
    // Řekni světu
    System.out.println( "Odstranění připojení "+s );
    // Odstranění z naší Hashtable / list
    outputStreams.remove( s );
    s.close();
} catch( IOException ie ) {
System.out.println( "Error closing "+s );
ie.printStackTrace();
}
}
}
// Hlavni program
static public void main( String args[] ) throws Exception {
// Získejte port # z příkazové řádky
int port = 5555;
// Vytvořte objekt serveru, který automaticky 
//začne přijímat připojení.
new Server( port );
}
}
