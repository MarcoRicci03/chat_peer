/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat_peer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marco
 */
public class Thread_manda_connessione extends Thread {

    private Socket client_socket;
    private manage_peer peer_control;
    private InputStream is;
    private OutputStream os;
    
    

    public Thread_manda_connessione(manage_peer peer_control) throws IOException {
        this.peer_control = peer_control;

    }

    public void run() {
        try {
            client_socket = new Socket(peer_control.getPeer_connesso().getIp_peer_connesso(), peer_control.getPeer_connesso().getPort_peer_connesso());
            //qua devo mandare il mio indirizzo ip e la mia porta preceduti
            PrintWriter out = new PrintWriter(client_socket.getOutputStream(), true);
            out.println("c;" + peer_control.getIp() + ";" + peer_control.getPort() + ";" + peer_control.getName());
            client_socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Thread_manda_connessione.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
