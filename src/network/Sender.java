package network;
import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Sender implements Runnable {
    private String msg;
    private Socket s;
    private DataOutputStream dos;
    private BufferedReader br;
    final int BUFFER_SIZE = 128;

    public Sender(Socket socket){
        s = socket;
        msg = "";
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
                System.out.println("press f or m to send: f - file; m - msg");
                String choice = br.readLine();
                if (choice.equals("m")){
                    sendMsg();
                }
                else if (choice.equals("f")){
                    sendFile();
                }
                else {
                    System.out.println("No f nor m. Try again.");
                }
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

    private void sendMsg(){
        try {
            dos.writeUTF("m");//type
            dos.flush();
            msg = br.readLine();
            dos.writeUTF(msg);
            dos.flush();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void sendFile(){
        System.out.println("Write file name: ");
        try {
            String path_name = br.readLine();
            FileInputStream fis = new FileInputStream(path_name);
            long file_size = Files.size(Paths.get(path_name));
            int reps = (int)(file_size/BUFFER_SIZE);
            if (file_size%BUFFER_SIZE!=0) reps+=1;
            byte file_chunk[] = new byte [BUFFER_SIZE];

            dos.writeUTF("f");//type
            dos.flush();
            dos.writeUTF(path_name);//file name
            dos.flush();
            dos.writeUTF(Long.toString(file_size));//file size
            dos.flush();
            dos.writeUTF(Integer.toString(BUFFER_SIZE));//buffer size
            dos.flush();
            dos.writeUTF(Integer.toString(reps));//reps
            dos.flush();

            for(int i=0;i<reps;i++){
                if(i==reps-1)file_chunk = new byte[(int)file_size-(reps-1)*BUFFER_SIZE];
                fis.read(file_chunk,0, file_chunk.length);
                dos.write(file_chunk);
                dos.flush();
            }
            fis.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
