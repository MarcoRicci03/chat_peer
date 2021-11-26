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
public class Thread_ricevi extends Thread {

    private Manage_peer mp;

    public Thread_ricevi(Manage_peer mp) {
        this.mp = mp;
    }

    public void run() {
        
            mp.ricevi_connessione();
        
    }

}
