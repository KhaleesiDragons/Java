
package bezpecnost;

import java.util.Enumeration;
import java.util.Hashtable;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author abilogul
 */
class TRIFID {
     private String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ#";
     private String text;
     private String key;
     private int grupa;
     private String keyabc;
     private String encrypt="";
     private String decrypt="";
     private Hashtable<String, Integer> ht=new Hashtable();
     
     TRIFID(String key, int grupa, String text){
     this.grupa=grupa;
     this.key=kontrolkey(kontrola(key.toUpperCase()));
     this.text=kontrola(text.toUpperCase());
     splitkey();
     createtables();
     }
     
     // metoda skontroluje text. Pokud tezt obsahuje jine znaky nez v abcede, tak jich smaze.
     String kontrola(String txt){
        String kontr="";
         for (int i = 0; i < txt.length(); i++) {
             if (abc.contains(String.valueOf(txt.charAt(i)))==true)
                 kontr=kontr+String.valueOf(txt.charAt(i));      
         }
         return kontr;
     }
     
  
     // skontroluje key na opakujici znaky
     String kontrolkey(String txt){
         String str="";
         for (int i = 0; i < txt.length(); i++) {
             if (str.contains(String.valueOf(txt.charAt(i)))==false)
             str=str+txt.charAt(i);
          //   else continue;
         }
         return str;
     }
     
     // metoda vytvori klic#abceda
     void splitkey(){     
     keyabc=key+"#";   
        for (int i = 0; i < abc.length(); i++) {
        if(keyabc.contains(String.valueOf( abc.charAt(i)))==false)
         keyabc=keyabc+String.valueOf(abc.charAt(i));       
        }    
     }
     
     // do hashtablu se ulozi znaky  s jich identifikacnim cislem
     void createtables(){
         int xxx=0;
         for (int s = 1; s < 4; s++) {           
                   
             for (int i = 1; i < 4; i++) {            
                        
                 for (int j = 1; j < 4; j++) {
             
                 if (s==1)  ht.put(String.valueOf(keyabc.charAt(xxx)), 100+Integer.parseInt(i+""+j));
                
                 if (s==2)  ht.put(String.valueOf(keyabc.charAt(xxx)), 200+Integer.parseInt(i+""+j));
                
               if (s==3)  ht.put(String.valueOf(keyabc.charAt(xxx)), 300+Integer.parseInt(i+""+j));
                 
                 xxx++; 
             }             
         }
     }
     }      
     
     // najit pismenko podle identifikacniho cisla
    String find_pismenko(int pismenko) {
        Enumeration e1 = ht.elements();
        Enumeration e2 = ht.keys();

        String text = null;
        while (e1.hasMoreElements() & e2.hasMoreElements()) {
            Integer fff = (Integer) e1.nextElement();
            String sss = (String) e2.nextElement();
           if (fff == pismenko) {
                return text = sss;
          }
        }
        return text;
    }
     
    
    
    // najit identifikacni cislo podle pismenku
    int find_cislo(String t){
    return ht.get(t);
    } 
    
    
    // text se rozlozi na bloci, v kazdy blok se rozpracuje samostatne
    void blok_encrypt(String txt){
        String cislo="";
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < txt.length(); j++) {
               int c=find_cislo(String.valueOf(txt.charAt(j)));                
              cislo=cislo+String.valueOf(c).charAt(i);
            }
        
        }   
    
        String newtext="";
        
        for (int i = 0; i < cislo.length(); i=i+3) {
           newtext=newtext+find_pismenko(Integer.parseInt(cislo.substring(i, i+3)));            
        }
        encrypt=encrypt+newtext;
   
    }
    

    // metoda encryption
     void encryption(){
         int zustatek=text.length()%grupa;
         int cislogrup=text.length()/grupa;
        // System.out.println( cislogrup+" "+text.length());

         int delka=text.length()-zustatek;
         for (int i = 0; i < delka; i=i+grupa) {
             String str=text.substring(i, i+grupa);
             blok_encrypt(str);
         }
 
        
         
         if (zustatek!=0)  blok_encrypt(text.substring(text.length()-zustatek, text.length()));   
         System.out.println(splitCipher(encrypt));
    
     }
     
     // metoda rozlozi text po 5 pismen 
     String splitCipher(String text) {

        String g = "";
        for (int i = 0; i < text.length(); i++) {
            if (i != 0) {
                if (i % 5 == 0) {
                    g = g + " ";
                }
            }
            g = g + text.substring(i, i + 1);
        }
        g = g;
        return g;
    
        }
     
     
      void blok_decrypt(String txt){
      
          String s="";
          for (int i = 0; i < txt.length(); i++) {
              s=s+find_cislo(String.valueOf(txt.charAt(i)));              
          }
       
          int delka=s.length()/3;
           String s1=s.substring(0, delka);
      
           String s2=s.substring(delka, 2*delka);
   
           String s3=s.substring(2*delka, 3*delka);
        
           String vysl;
           
           int count=0;
           for (int i = 0; i < delka; i++) {
               vysl=""+s1.charAt(count)+s2.charAt(count)+s3.charAt(count);               
               decrypt=decrypt+ find_pismenko(Integer.parseInt(vysl));
               count++;
           }
          
          
      }
      // metoda decryption
      void decrypt(){
      int zustatek=text.length()%grupa;
         int cislogrup=text.length()/grupa;
        // System.out.println( cislogrup+" "+text.length());
        
             int delka=text.length()-zustatek;
         for (int i = 0; i < delka; i=i+grupa) {
             String str=text.substring(i, i+grupa);
             blok_decrypt(str);
         }      
         if (zustatek!=0)  blok_decrypt(text.substring(text.length()-zustatek, text.length()));   
         System.out.println(decrypt);
      }
     

         
}

class Bezpecnost {

    String type;
    StringTokenizer st = new StringTokenizer("");
    BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

    boolean run() throws Exception {
        type = nextToken();
        if (type.equals("end")) {
            return false;
        }
        if (type.equals("e")) {
            String key = nextToken();
            String message = "";
            while (st.hasMoreTokens()) {
                message = message + st.nextToken();
            }
            String[] keys = new String[2];
            keys = getKey(key);
            TRIFID e = new TRIFID(keys[0], Integer.parseInt(keys[1]), message);
            e.encryption();
            return true;
        }
        if (type.equals("d")) {
            String key = nextToken();
            String message = "";
            while (st.hasMoreTokens()) {
                message = message + st.nextToken();
            }
            String[] keys = new String[2];
            keys = getKey(key);
            TRIFID d = new TRIFID(keys[0], Integer.parseInt(keys[1]), message);
            d.decrypt();
            return true;
        }
        return true;
    }

    public static void main(String args[]) throws Exception {
        
            Bezpecnost inst = new Bezpecnost();
          
            while (inst.run()) {
            }
   
        
      
    }

    String nextToken() throws Exception {
        while (!st.hasMoreTokens()) {
            st = new StringTokenizer(stdin.readLine());
        }
        return st.nextToken();
    }

    public static String[] getKey(String cipher) {
        String[] keys = new String[2];
        keys = cipher.split("QQQ");
        keys[0] = keys[0].substring(0, keys[0].length() - 2);;
        keys[1] = keys[1].substring(2);
        return keys;
    }
}
