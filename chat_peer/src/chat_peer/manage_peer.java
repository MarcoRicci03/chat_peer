/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat_peer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
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

    public Manage_peer(String name, String ip, Integer port) throws IOException {
        this.ip = ip;
        this.port = port;
        this.name = name;
        connesso = false;

        port_peer_connesso = null;
    }

    //get
    public String getIp() {
        return ip;
    }

    public Integer getPort() {
        return port;
    }

    public String getName() {
        return name;
    }

    public void ricevi_connessione(int i) throws SocketException {
        byte[] data = new byte[1500];
        DatagramPacket p = new DatagramPacket(data, data.length);
        DatagramSocket s = new DatagramSocket(port);
        String[] vect;
        do {
            try {
                s.receive(p);
                if (port_peer_connesso != null) {
                    if (p.getPort() != port_peer_connesso) {
                        //mando pacchetto dicendo che il peer sta giá comunicando con un altro peer
                    }
                }
                String str = new String(data);
                vect = str.split(";"); //c;ip;porta;nome
                switch (vect[0].toLowerCase()) {
                    case "c" -> {
                        //vuole effettuare una connessione
                        if (!connesso) {//controllo se siamo connessi con un'altro peer al momento
                            //salvo i dati dell'altro peer
                            nome_peer_connesso = vect[3];
                            port_peer_connesso = Integer.parseInt(vect[2]);
                            connesso = true;
                            ip_peer_connesso = vect[1];
                            System.out.println("SERVER: Connessione avvenuta con: " + nome_peer_connesso
                                    + " la cui porta è " + port_peer_connesso + " e l'indirizzo: " + ip_peer_connesso);

                            manda_connessione(port_peer_connesso, ip_peer_connesso, 0);

                        }
                    }
                    case "m" -> {
                        if (connesso) {
                            System.out.println("[" + nome_peer_connesso + "]: " + vect[1]);
                        } else {
                            System.out.println("Devi prima connetterti a un peer");
                        }
                    }
                    case "d" -> {

                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Manage_peer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);

    }

    //metodo per mandare una connessione
    public void manda_connessione(Integer port_peer_connesso, String ip_peer_connesso, int i) throws SocketException, IOException {
        this.ip_peer_connesso = ip_peer_connesso;
        this.port_peer_connesso = port_peer_connesso;

        //qua devo mandare il mio indirizzo ip e la mia porta preceduti
        byte[] data = new byte[1500];
        data = ("c;" + ip + ";" + port + ";" + name).getBytes();
        DatagramPacket p = new DatagramPacket(data, data.length);
        DatagramSocket s = new DatagramSocket();
        p.setAddress(InetAddress.getByName(ip_peer_connesso));
        p.setPort(port_peer_connesso);
        s.send(p);

    }

    public void manda_messaggi() {

    }

    public void ricevi_messaggi() {

    }

}
