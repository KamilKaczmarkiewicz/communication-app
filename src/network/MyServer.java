package network;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

    private ServerSocket ss;
    private Socket s;

    public MyServer(){
        this(3333);
    }

    public MyServer(int port){
        try {
            ss = new ServerSocket(port);
            System.out.println("Server started");
            s = ss.accept();
            System.out.println("Someone joined server");

            Receiver receiver = new Receiver(s);
            Sender sender = new Sender(s);
            Thread t_sender = new Thread(sender);
            Thread t_receiver = new Thread(receiver);
            t_receiver.start();
            t_sender.start();
            t_receiver.join();
            t_sender.join();

            s.close();
            ss.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

}
