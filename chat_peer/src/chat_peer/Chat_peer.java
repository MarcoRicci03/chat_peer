/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chat_peer;

import java.io.IOException;
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

        System.out.println("Inserire il nome del peer e dopo aver premuto invio inserire la porta (indirizzo settato di default a localhost).");
        Manage_peer mp = new Manage_peer(s.nextLine(), "localhost", s.nextInt());//String name, String ip, Integer port
        Thread_peer tp = new Thread_peer(mp);
        Thread_ricevi tr = new Thread_ricevi(mp);
        tr.start();
        tp.start();
    }

}
