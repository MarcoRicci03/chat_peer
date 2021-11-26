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
            if (mp.getScrivendo()) {
                str = "m";
            } else {
                str = s.nextLine();
            }

            switch (str.toLowerCase()) {
                case "c" -> {
                    //Connessione con altro peer
                    mp.manda_connessione(s.nextInt(), "localhost");
                }

                case "m" -> {
                    //Inizia a messaggiare
                    if (mp.getConnesso()) {
                        mp.manda_messaggi();
                    } else {
                        System.out.println("Collegati a un peer prima.");
                    }
                }

                case "d" -> {
                    //Chiusura con peer

                }

            }

        } while (true);

    }
}
