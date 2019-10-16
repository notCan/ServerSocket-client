import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Client {

  public final static int port = 2048;      
  public final static String server = "192.168.43.147";  
  public final static String dosya = "C:\\Users\\1905d\\Desktop\\Al\\abcd.png";  
  
  public final static int boyut = 6022386; 

  public static void main (String [] args ) throws IOException {
    int bRead;
    int crnt = 0;
    FileOutputStream fo = null;
    BufferedOutputStream bo = null;
    Socket sock = null;
    try {
      sock = new Socket(server, port);
      System.out.println("Bağlanıyor.");

      // receive file
      byte [] b  = new byte [boyut];
      InputStream is = sock.getInputStream();
      fo = new FileOutputStream(dosya);
      bo = new BufferedOutputStream(fo);
      bRead = is.read(b,0,b.length);
      crnt = bRead;

      do {
         bRead =
            is.read(b, crnt, (b.length-crnt));
         if(bRead >= 0) crnt += bRead;
      } while(bRead > -1);

      bo.write(b, 0 , crnt);
      bo.flush();
      System.out.println("Dosya " + dosya
          + " indirildi");
    }
    finally {
      if (fo != null) fo.close();
      if (bo != null) bo.close();
      if (sock != null) sock.close();
    }
  }

}