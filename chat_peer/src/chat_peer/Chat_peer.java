/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chat_peer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.util.Scanner;

/**
 *
 * @author marco
 */
public class Chat_peer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        System.out.println("Inserire la porta e l'indirizzo di questo peer nel formato ip:porta : ");
        String[] v = s.nextLine().split(":");
        manage_peer peer_control = new manage_peer();

        //thread_ricevi tR = new thread_ricevi(peer_control);
        thread_manda tM = new thread_manda(peer_control);

        

        //tR.start();
        tM.start();
    }

}
