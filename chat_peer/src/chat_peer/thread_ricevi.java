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
public class thread_ricevi extends Thread {

    ServerSocket server_socket;
    manage_peer peer_control;

    public thread_ricevi(manage_peer peer_control) throws IOException {
        this.peer_control = peer_control;
        server_socket = new ServerSocket(peer_control.getPort());

    }

    public void run() {

        System.out.println("Il peer é in ascolto...");
        try {
            while (true) {
                Socket s = server_socket.accept();
                InputStreamReader isr = new InputStreamReader(s.getInputStream());
                BufferedReader br = new BufferedReader(isr);
                
                System.out.println("SERVER: " + br.readLine());
                System.out.println(s.getPort());
                s.close();
                
            }
        } catch (IOException ex) {
            Logger.getLogger(thread_ricevi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
