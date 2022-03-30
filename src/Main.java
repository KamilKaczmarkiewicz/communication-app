import network.Network;

import java.util.Scanner;

public class Main {

    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        System.out.println("Write \"1\" if u want to start as the SERVER");
        System.out.println("Write \"2\" if u want to start as the CLIENT");
        while (true){
            String choice = in.next();
            if (choice.equals("1")){
                Network network = new Network(true);
                break;
            }
            else if (choice.equals("2")){
                Network network = new Network(false);
                break;
            }
            System.out.println("Wrong input!!! Try again");
        }

    }
}
