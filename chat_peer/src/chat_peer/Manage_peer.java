/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat_peer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marco
 */
public class Manage_peer {

    private String ip, name;
    private Integer port;

    //informazioni peer connesso
    private String nome_peer_connesso;
    private Integer port_peer_connesso;
    private Boolean connesso;
    private String ip_peer_connesso;

    public Manage_peer(String name, String ip, Integer port) throws IOException {
        this.ip = ip;
        this.port = port;
        this.name = name;
        connesso = false;

        port_peer_connesso = null;
    }

    //get
    public String getNome_peer_connesso() {
        return nome_peer_connesso;
    }

    public Integer getPort_peer_connesso() {
        return port_peer_connesso;
    }

    public Boolean getConnesso() {
        return connesso;
    }

    public String getIp_peer_connesso() {
        return ip_peer_connesso;
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

    public void chiudi_connessione() {
        nome_peer_connesso = null;
        port_peer_connesso = null;
        connesso = false;
        ip_peer_connesso = null;
    }

}
