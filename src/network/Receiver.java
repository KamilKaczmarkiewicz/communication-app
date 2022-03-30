package network;
import java.io.DataInputStream;
import java.net.Socket;

public class Receiver implements Runnable {
    private String msg;
    private Socket s;
    private DataInputStream din;

    public Receiver(Socket socket){
        s = socket;
        try {
            din = new DataInputStream(s.getInputStream());
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void run() {
        do {
            try {
                msg = din.readUTF();
            }catch (Exception e){
                System.out.println(e);
            }
            System.out.println("Second person says: " + msg);
        }while (! msg.equals("exit"));
        // ending connection
        System.out.println("Ending conversation");
        try {
            din.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
