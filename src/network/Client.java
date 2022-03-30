package network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

    private Socket s;
    private DataInputStream din;
    private DataOutputStream dos;
    private BufferedReader br;
    private String my_msg;
    private String received_msg;

    public Client(){
        this("localhost", 3333);
    }

    public Client(String host, int port){
        try {
            s = new Socket(host, port);
            System.out.println("Successfully joined server!");
            din = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
            br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Say something: ");
            my_msg = br.readLine();
            dos.writeUTF(my_msg);
            dos.flush();
            // ending connection
            din.close();
            s.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

}
