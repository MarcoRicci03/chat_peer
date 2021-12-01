/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat_peer;

import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marco
 */
public class Thread_ricevi extends Thread {

    private Manage_peer mp;

    public Thread_ricevi(Manage_peer mp) {
        this.mp = mp;
    }

    public void run() {
        int i = 0;
        do {
            try {
                mp.ricevi_connessione();
            } catch (SocketException ex) {
                Logger.getLogger(Thread_ricevi.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

}
