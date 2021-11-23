/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat_peer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author marco
 */
public class manage_peer {

    private Socket client_socket;
    private ServerSocket server_socket;
    private String ip;
    private Integer port;

    public manage_peer() throws IOException {
        client_socket = new Socket();
        server_socket = new ServerSocket();
    }
    
    
    //get
    public Socket getClient_socket() {
        return client_socket;
    }
    public ServerSocket getServer_socket() {
        return server_socket;
    }
    public String getIp() {
        return ip;
    }
    public Integer getPort() {
        return port;
    }
    
}
