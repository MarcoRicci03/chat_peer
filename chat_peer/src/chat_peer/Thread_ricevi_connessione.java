/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat_peer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marco
 */
public class Thread_ricevi_connessione extends Thread {

    private ServerSocket server_socket;
    private manage_peer peer_control;

    public Thread_ricevi_connessione(manage_peer peer_control) throws IOException {
        this.peer_control = peer_control;
        server_socket = new ServerSocket(peer_control.getPort());

    }

    public void run() {
        String[] vect;
        Socket s;
        InputStreamReader isr;
        System.out.println("Il peer é in ascolto...");
        try {
            while (true) {
                s = server_socket.accept();
                isr = new InputStreamReader(s.getInputStream());
                BufferedReader br = new BufferedReader(isr);
                vect = br.readLine().split(";"); //c;ip;porta;nome

                System.out.println("SERVER: Connessione avvenuta con: " + vect[3] + " la cui porta è " + vect[2] + " e l'indirizzo: " + vect[1]);
                peer_control.setPeer_connesso(vect[1], Integer.parseInt(vect[2]));
            
         
            }
        } catch (IOException ex) {
            Logger.getLogger(Thread_ricevi_connessione.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
