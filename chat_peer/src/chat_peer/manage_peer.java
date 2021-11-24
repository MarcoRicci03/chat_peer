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
    private String ip, name;
    private Integer port;

    private PeerConnesso peer_connesso;

    public manage_peer(String name, String ip, Integer port) throws IOException {
        client_socket = new Socket();
        server_socket = new ServerSocket();
        this.ip = ip;
        this.port = port;
        this.name = name;

    }

    public void setPeer_connesso(String ip_peer_connesso, Integer port_peer_connesso) {
        peer_connesso = new PeerConnesso(ip_peer_connesso, port_peer_connesso);
    }

    //get
    public Socket getClient_socket() {
        return client_socket;
    }

    public PeerConnesso getPeer_connesso() {
        return peer_connesso;
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

    public String getName() {
        return name;
    }

}
