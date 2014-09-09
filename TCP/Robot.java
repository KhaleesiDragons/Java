package robot;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author G
 */
public class Robot implements Runnable {

    private Socket s;
    private Socket client;
    private Thread Thread = null;
    private int port;
    private int[] fotoPole = null;

    public Robot(int port) {
        this.port = port;
    }

    
     private void Message() throws IOException {
        PrintWriter out = null;
        BufferedInputStream dis = null;

        try {
            s.setSoTimeout(20000);
        } catch (SocketException ex) {
            Logger.getLogger(Robot.class.getName()).log(Level.SEVERE, null, ex);
        }
        out = new PrintWriter(this.client.getOutputStream(), true);
        dis = new BufferedInputStream(this.client.getInputStream());

        login_heslo(dis, out, (int) Thread.getId());
        info_foto(dis, out, (int) Thread.getId());

        closeConnection(dis, out);
        s.close();

    }
     
     
   

    // metoda zjisti jmeno a heslo robota
    private void login_heslo(BufferedInputStream dis, PrintWriter out, int r) {
        try {
            String commandLogin = "";
            String commandHeslo = "";
            sendOK(r, dis, out, "200 LOGIN" + "\r\n");
            commandLogin = this.readIn(dis).trim();
            sendOK(r, dis, out, "201 PASSWORD" + "\r\n");
            commandHeslo = this.readIn(dis).trim();
            //  System.out.println(r + " Heslo >>>" + commandHeslo);
            // System.out.println(r + " Login >>>" + calculatePassword(commandLogin));
            if (commandLogin.length() == 0 && commandHeslo.length() == 0) {
                sendOK(r, dis, out, "500 LOGIN FAILED" + "\r\n");
                this.closeConnection(dis, out);
            }
            if (!commandLogin.startsWith("Robot")) {
                sendOK(r, dis, out, "500 LOGIN FAILED" + "\r\n");
                this.closeConnection(dis, out);
            }
            if (calculatePassword(commandLogin).equals(commandHeslo) == true) {
                sendOK(r, dis, out, "202 OK" + "\r\n");

            } else {
                sendOK(r, dis, out, "500 LOGIN FAILED" + "\r\n");
                this.closeConnection(dis, out);
            }
            // System.out.println(r + " >>>>> " + calculatePassword(commandLogin.trim()).equals(commandHeslo));
        } catch (IOException ex) {
            try {
                sendOK(r, dis, out, "500 LOGIN FAILED" + "\r\n");
            } catch (IOException ex1) {
                System.out.println("can't send");
            }
            this.closeConnection(dis, out);
        }

    }

    public void info_foto(BufferedInputStream dis, PrintWriter out, int r) throws NullPointerException {
        try {
            String command = "";

            int i = 0;
            while (i != 5) {
                long foto = dis.read();
                command = command + "" + (char) foto;
                i++;
            }

            if (command.startsWith("INFO ")) {
                command = readIn(dis);
                if (command.startsWith("INFO X")) {
                    sendOK(r, dis, out, "501 SYNTAX ERROR" + "\r\n");
                    closeConnection(dis, out);
                } else {
                    sendOK(r, dis, out, "202 OK" + "\r\n");
                    info_foto(dis, out, r);
                }
                sendOK(r, dis, out, "202 OK" + "\r\n");
                info_foto(dis, out, r);
            }

            if (command.startsWith("FOTO ")) {
                System.out.println("Thread " + r + " FOTO ///////////////// ");
                boolean foto = readFoto(dis, r);
                if (foto) {
                    sendOK(r, dis, out, "202 OK" + "\r\n");
                    info_foto(dis, out, r);
                } else {
                    sendOK(r, dis, out, "300 BAD CHECKSUM" + "\r\n");
                    info_foto(dis, out, r);
                }
            }

            //  System.out.println(r + " >>> 501 SYNTAX ERROR");
            sendOK(r, dis, out, "501 SYNTAX ERROR" + "\r\n");
            closeConnection(dis, out);
        } catch (IOException ex) {
            try {

                sendOK(r, dis, out, "501 SYNTAX ERROR" + "\r\n");
                closeConnection(dis, out);

            } catch (Exception ex1) {
                System.out.println("can't send");
            }
        }

    }

   

    // vypocita heslo podle jmena robota
    private String calculatePassword(String commandLogin) {
        int cislo = 0;
        String password = commandLogin;
        int j = 0;
        for (int i = 0; i < password.length(); i++) {
            int count = (int) password.charAt(i);
            cislo += count;
        }
        return String.valueOf(cislo);//String.valueOf(cislo);
    }

    // zavrit spojeni
    private void closeConnection(BufferedInputStream dis, PrintWriter out) {
        try {
            dis.close();
            out.close();
        } catch (IOException ex) {
            System.out.println("close connection");
        }
    }

    // posila prikazy
    private static void sendOK(int r, BufferedInputStream dis, PrintWriter out, String text)
            throws IOException, NullPointerException {
        System.out.print("Thread " + r + " >>> " + text);
        out.print(text);
        out.flush();
    }

    // precist libovolny text ktery konci \r\n krome fotky
    private String readIn(BufferedInputStream dis) throws IOException {
        StringBuilder sb = new StringBuilder();
        boolean endCmd = false;
        char c;
        while (!endCmd) {
            c = (char) dis.read();

            if (c == '\r') {
                char a = (char) dis.read();

                if (a == '\n') {
                    endCmd = true;
                } else {
                    sb.append(a);
                }
            }
            sb.append(c);
        }
        return sb.toString().trim();
    }

    // precist fotku a kontrolnz soucet
    private boolean readFoto(BufferedInputStream dis, int r) throws IOException {

        StringBuilder sb = new StringBuilder();
        boolean endCmd = false;
        char c;
        int pocetZnaku = 0;
        int space = 0;
        long dataSumma_plus_souchet = 0;
        long dataSumma_bez_souchet = 0;
        String cheksum = "";
        List<Integer> cheksum_pole = new ArrayList();
        // byte[] dataPole =new byte[Integer.valueOf(cheksum)];

        while (!endCmd) {
            if (dis.available() <= 0) {
                endCmd = true;
            } else {
                long p = dis.read();
                c = (char) p;
                //  System.out.println(r + " = " + p + " = " + c);
                if (space != 1) {
                    if (c == ' ') {
                        space++;
                        fotoPole = new int[Integer.valueOf(cheksum)];
                        // System.out.println(fotoPole.length);
                        continue;
                    }
                    cheksum = cheksum + "" + c;
                } else {

                    //    System.out.println(r + " pocetZnaku " + pocetZnaku + " === " + p + " === " + (char) p);
                    if ((pocetZnaku + 1) <= Integer.valueOf(cheksum)) {
                        fotoPole[pocetZnaku] = (int) p;
                    }
                    if (cheksum.equals((pocetZnaku + 1) + "")) {
                        dataSumma_plus_souchet = dataSumma_plus_souchet + p;
                        dataSumma_bez_souchet = dataSumma_plus_souchet;

                    } else {
                        dataSumma_plus_souchet = dataSumma_plus_souchet + p;

                    }

                    if ((pocetZnaku + 1) > Integer.parseInt(cheksum)) {
                        //   System.out.println("pocet >>> " + pocetZnaku + "cheksum >>> " + Integer.parseInt(cheksum) + " SOUCET >>" + p);
                        dataSumma_plus_souchet = dataSumma_plus_souchet + p;
                        cheksum_pole.add((int) p);

                        if (cheksum_pole.size() == 4) {
                            endCmd = true;
                        }

                    }

                    pocetZnaku++;
                }

                continue;

            }
        }
        //  System.out.println(r + "pocet >>> " + pocetZnaku + " cheksum_pole >>> " + cheksum_pole.size());
        if (cheksum_pole.size() < 2) {

            boolean bool_sum = false;

            while (!bool_sum) {
                long p = dis.read();
                c = (char) p;
                //    System.out.println(r + " = " + p + " = " + c);
                cheksum_pole.add((int) p);
                dataSumma_plus_souchet = dataSumma_plus_souchet + p;
                pocetZnaku++;
                if (cheksum_pole.size() == 4 || dis.available() <= 0) {
                    bool_sum = true;
                }
            }

        }
        System.out.println("Thread " + r + " delka foto >>> " + this.fotoPole.length);
        System.out.println("Thread " + r + " convert >>> " + convert(cheksum_pole));
        System.out.println("Thread " + r + " Delka >>> " + cheksum);
        System.out.println("Thread " + r + " cheksum_pole >>> " + cheksum_pole.size());
        System.out.println("Thread " + r + " dataSumma_bez_souchet >>> " + dataSumma_bez_souchet);
        System.out.println("Thread " + r + " dataSumma plus souchet >>> " + dataSumma_plus_souchet);

        if (convert(cheksum_pole) == dataSumma_bez_souchet) {
            System.out.println("--------------------------------------------------------------");
          //  this.PhotoDownloader(fotoPole);
            return true;
        }
        System.out.println("--------------------------------------------------------------");
        return false;
    }

    // konvertace kontrolny soucet  HEX to INT
    public long convert(List<Integer> cheksum_pole) {
        if (cheksum_pole.size() == 0) {
            return 0;
        }
        String sum = "";
        for (int i = 0; i < cheksum_pole.size(); i++) {

            String Hex = Integer.toHexString(cheksum_pole.get(i));
            sum = sum + "" + Hex;
        }
        //System.out.println(" Hex >>>" + sum);
        long convert = Integer.parseInt(sum, 16);
        return convert;
    }

     // save photo
    void PhotoDownloader(int[] dataPole) throws FileNotFoundException, IOException {
        Random r = new Random();
        File f = new File("foto" + r.nextInt(1000) + ".png");
        FileOutputStream fos = new FileOutputStream(f);
        for (int i = 0; i < dataPole.length; i++) {
            fos.write(dataPole[i]);
        }
        fos.close();
    }
    
    
    // pripojeni s klientem/robotem
    public static void main(String[] arg) {
        try {
            // int port=Integer.parseInt(arg[0]);
            int port = 3600;
            ServerSocket ss = new ServerSocket(port);
            while (true) {
                Robot srv = new Robot(port);
                srv.client = ss.accept();
                srv.s = srv.client;
                srv.start();

            }
        } catch (IOException ex) {
            System.out.println("error connect");
        }
    }

    @Override
    public void run() {
        try {
            Message();
        } catch (IOException ex) {
        }

    }

    private void start() {
        if (Thread == null) {
            Thread = new Thread(this);
            Thread.start();
        }

    }

    public void stop() {
        Thread = null;
    }

}
