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

    Socket client_socket;
    ServerSocket server_socket;
    String ip;
    Integer port;

    public manage_peer(String ip, Integer port) throws IOException {
        client_socket = new Socket();
        server_socket = new ServerSocket();
        this.ip = ip;
        this.port = port;
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
