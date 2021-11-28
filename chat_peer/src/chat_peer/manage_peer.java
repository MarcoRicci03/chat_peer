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

    public void ricevi_connessione(int i) throws SocketException {
        int a = 0;
        byte[] data;
        DatagramPacket p;
        DatagramSocket s = new DatagramSocket(port);
        String[] vect;
        do {
            try {
                data = new byte[1500];
                p = new DatagramPacket(data, data.length);
                s.receive(p);
                if (port_peer_connesso != null) {
                    if (p.getPort() != port_peer_connesso) {
                        //mando pacchetto dicendo che il peer sta giá comunicando con un altro peer
                        mando_errore(p.getAddress(), p.getPort()); //problema con la porta, non viene settata
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

                            manda_connessione(port_peer_connesso, ip_peer_connesso);
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
                    case "r" -> {
                        System.out.println(vect[1]);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Manage_peer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);

    }

    //metodo per mandare una connessione
    public void manda_connessione(Integer port_peer_connesso, String ip_peer_connesso) throws SocketException, IOException {
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

    public void mando_errore(InetAddress ip_destinatario, Integer port_destinatario) throws SocketException, UnknownHostException, IOException {
        byte[] data = new byte[1500];
        data = ("r;Il peer con a cui stai cercando di connetterti e' occupato").getBytes();
        DatagramPacket p = new DatagramPacket(data, data.length);
        DatagramSocket s = new DatagramSocket();
        p.setAddress(ip_destinatario);
        p.setPort(port_destinatario);
        s.send(p);
    }

    public void manda_messaggi() throws SocketException, UnknownHostException, IOException {
        String str = "";
        byte[] data;
        Scanner tastiera;
        DatagramPacket p;
        DatagramSocket s;
        do {
            data = new byte[1500];
            tastiera = new Scanner(System.in);
            System.out.println("[" + name + "]");
            str = tastiera.nextLine();
            if (!str.equals("stop")) {
                data = ("m;" + str).getBytes();
                p = new DatagramPacket(data, data.length);
                s = new DatagramSocket();
                p.setAddress(InetAddress.getByName(ip_peer_connesso));
                p.setPort(port_peer_connesso);
                s.send(p);
            }
        } while (!str.equals("stop"));
    }

    public void chiudi_connessione() {
        nome_peer_connesso = null;
        port_peer_connesso = null;
        connesso = false;
        ip_peer_connesso = null;
    }

}
