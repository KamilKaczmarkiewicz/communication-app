package network;

public class Network {

    /**
     * @param isServer true if start as server, false if start as client
     * */
    public Network(boolean isServer){
        if (isServer){
            System.out.println("Starting as SERVER");
            MyServer ms = new MyServer();
        }else{
            System.out.println("Starting as CLIENT");
            Client c = new Client();
        }
    }
}
