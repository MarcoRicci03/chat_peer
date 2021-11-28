/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat_peer;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marco
 */
public class Thread_peer extends Thread {

    private Manage_peer mp;
    private Scanner s;

    public Thread_peer(Manage_peer mp) {
        this.mp = mp;
        s = new Scanner(System.in);
    }

    public void run() {
        String str;
        do {
            System.out.println("C-Connettiti con un'altro peer.");
            System.out.println("M-Inizia a scrivere con il peer connesso.");
            System.out.println("D-Chiudi la connessione con il peer.");
            System.out.println("(Indifferente se la lettera é maiuscola o minuscola)");
            s = new Scanner(System.in);

            switch (s.nextLine().toLowerCase()) {
                case "c" -> {
                    try {
                        //Connessione con altro peer
                        //System.out.println("Inserire r se si vuole ricevere una richiesta o m se si vuole mandare una richiesta: ");
                        // str = s.nextLine();
                        //if (str.equalsIgnoreCase("r")) {
                        //     mp.ricevi_connessione(1);
                        // } else if (str.equalsIgnoreCase("m")) {
                        System.out.println("Inserisci la porta del destinatario");
                        mp.manda_connessione(s.nextInt(), "localhost");
                        //  }
                    } catch (IOException ex) {
                        Logger.getLogger(Thread_peer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                case "m" -> {
                    //Inizia a messaggiare
                    mp.manda_messaggi();
                }

                case "d" -> {
                    //Chiusura con peer

                }

            }

        } while (true);

    }
}
