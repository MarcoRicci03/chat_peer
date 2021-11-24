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
        System.out.println("Inserire il nome del peer: ");
        String name = s.nextLine();
        System.out.println("Inserire la porta e l'indirizzo di questo peer nel formato ip:porta : ");
        String[] v = s.nextLine().split(":");
        manage_peer peer_control = new manage_peer(name, v[0], Integer.parseInt(v[1]));

        Thread_ricevi_connessione tR = new Thread_ricevi_connessione(peer_control);
        Thread_manda_connessione tM = new Thread_manda_connessione(peer_control);

        System.out.println("Vuoi attendere altri peer o mandare un messaggio di ricerca? m-manda r-ricevi");
        String str = s.nextLine();
        if (str.equalsIgnoreCase("m")) {
            System.out.println("Inserire prima l'ip del destinatario, dopo la porta: ");
            peer_control.setPeer_connesso(s.nextLine(), s.nextInt());
            tM.start();
        } else if (str.equalsIgnoreCase("r")) {

            tR.start();
        }

    }

}
