/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat_peer;

import java.util.Scanner;

/**
 *
 * @author marco
 */
public class Thread_peer extends Thread {

    private Manage_peer mp;
    private Scanner s;
    private Thread_ricevi tr;

    public Thread_peer(Manage_peer mp) {
        this.mp = mp;
        s = new Scanner(System.in);
        tr = new Thread_ricevi(this.mp);
    }

    public void run() {
        tr.start();
        do {
            System.out.println("C-Connettiti con un'altro peer.");
            System.out.println("M-Inizia a scrivere con un altro peer.");
            System.out.println("D-Chiudi la connessione con il peer.");
            System.out.println("(Indifferente se la lettera é maiuscola o minuscola)");

            switch (s.nextLine().toLowerCase()) {
                case "c" -> {
                    //Connessione con altro peer
                    mp.manda_connessione(s.nextInt(), "localhost");
                }

                case "m" -> {
                    //Inizia a messaggiare

                }

                case "d" -> {
                    //Chiusura con peer

                }

            }

        } while (true);

    }
}
