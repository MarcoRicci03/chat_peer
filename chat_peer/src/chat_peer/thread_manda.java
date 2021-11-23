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
public class thread_manda extends Thread {

    Socket client_socket;
    manage_peer peer_control;
    InputStream is;
    OutputStream os;

    public thread_manda(manage_peer peer_control) throws IOException {
        this.peer_control = peer_control;

    }

    public void run() {
        try {
            client_socket = new Socket("localhost", 666);
            //qua devo mandare il mio indirizzo ip e la mia porta preceduti
            PrintWriter out = new PrintWriter(client_socket.getOutputStream(), true);
            out.println("c;" + peer_control.getIp() + ";" + peer_control.getPort());
            client_socket.close();
        } catch (IOException ex) {
            Logger.getLogger(thread_manda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
