package network;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Sender implements Runnable {
    private String msg;
    private Socket s;
    private DataOutputStream dos;
    private BufferedReader br;

    public Sender(Socket socket){
        s = socket;
        try {
            dos = new DataOutputStream(s.getOutputStream());
            br = new BufferedReader(new InputStreamReader(System.in));
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void run() {
        do {
            try {
                msg = br.readLine();
                dos.writeUTF(msg);
                dos.flush();
            }catch (Exception e){
                System.out.println(e);
            }
        }while (! msg.equals("exit"));
        // ending connection
        System.out.println("Ending conversation");
        try {
            dos.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void send(Socket s, String msg){
        try {
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            dos.writeUTF(msg);
            dos.flush();
            dos.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
