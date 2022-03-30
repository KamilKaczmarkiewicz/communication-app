package network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

    private ServerSocket ss;
    private Socket s;
    private DataInputStream din;
    private DataOutputStream dos;
    private BufferedReader br;
    private String my_msg;
    private String received_msg;

    public MyServer(){
        this(3333);
    }

    public MyServer(int port){
        try {
            ss = new ServerSocket(port);
            System.out.println("Server started");
            s = ss.accept();
            System.out.println("Someone joined server");
            din = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
            br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Waiting for msg from client");
            received_msg = din.readUTF();
            System.out.println("Client says: " + received_msg);
            // ending connection
            din.close();
            s.close();
            ss.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

}
