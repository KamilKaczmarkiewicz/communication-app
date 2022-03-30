package network;
import java.net.Socket;

public class Client {

    private Socket s;

    public Client(){
        this("localhost", 3333);
    }

    public Client(String host, int port){
        try {
            s = new Socket(host, port);
            System.out.println("Successfully joined server!");

            Receiver receiver = new Receiver(s);
            Sender sender = new Sender(s);
            Thread t_sender = new Thread(sender);
            Thread t_receiver = new Thread(receiver);
            t_receiver.start();
            t_sender.start();
            t_receiver.join();
            t_sender.join();

            s.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

}
