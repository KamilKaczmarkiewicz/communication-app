package network;
import java.io.DataInputStream;
import java.io.FileOutputStream;
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
                if (msg.equals("m")) {
                    receiveMsg();
                }
                else if (msg.equals("f")) {
                    receiveFile();
                }
                else {
                    System.out.println("Something went terribly wrong!: " + msg);
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }while (! msg.equals("exit"));
        // ending connection
        System.out.println("Ending conversation");
        try {
            din.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void receiveMsg() {
        try {
            msg = din.readUTF();
            System.out.println("Second person says: " + msg);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void receiveFile() {
        try {
            String path_name = din.readUTF();
            System.out.println("Receiving file: " + path_name);
            String fs = din.readUTF();
            long file_size = Long.parseLong(fs);
            System.out.println("FS: " + fs);
            System.out.println("File size: " + file_size);
            String bs = din.readUTF();
            int BUFFER_SIZE = Integer.parseInt(bs);
            System.out.println("bs: " + bs);
            System.out.println("BUFFER_SIZE: " + BUFFER_SIZE);
            String r = din.readUTF();
            int reps = Integer.parseInt(r);
            System.out.println("R: " + r);
            System.out.println("Reps: " + reps);

            FileOutputStream fout = new FileOutputStream("(rec)"+path_name);
            byte file_chunk[] = new byte [BUFFER_SIZE];
            for(int i=0;i<reps;i++){
                if(i==reps-1)file_chunk = new byte[(int)file_size-(reps-1)*BUFFER_SIZE];
                din.read(file_chunk);
                fout.write(file_chunk);
            }
            fout.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
