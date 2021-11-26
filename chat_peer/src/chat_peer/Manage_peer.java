/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat_peer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marco
 */
public class Manage_peer {

    private Socket client_socket;
    private ServerSocket server_socket;
    private String ip, name;
    private Integer port;

    //informazioni peer connesso
    private String nome_peer_connesso;
    private Integer port_peer_connesso;
    private Boolean connesso;
    private String ip_peer_connesso;

    private Boolean scrivendo;

    public Manage_peer(String name, String ip, Integer port) throws IOException {
        client_socket = new Socket();
        this.ip = ip;
        this.port = port;
        this.name = name;
        server_socket = new ServerSocket(this.port);
        connesso = false;
        scrivendo = false;
    }

    //get
    public Boolean getScrivendo() {
        return scrivendo;
    }

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

    public String getName() {
        return name;
    }

    public void ricevi_connessione() {
        String[] vect;
        InputStreamReader isr;
        try {
            client_socket = server_socket.accept();
            do {

                isr = new InputStreamReader(client_socket.getInputStream());
                BufferedReader br = new BufferedReader(isr);
                vect = br.readLine().split(";"); //c;ip;porta;nome
                switch (vect[0].toLowerCase()) {
                    case "c" -> {
                        //vuole effettuare una connessione
                        if (connesso) {//controllo se siamo connessi con un'altro peer al momento

                        } else {
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
                        System.out.println("[" + nome_peer_connesso + "]: " + vect[1]);
                        if (vect[1].equalsIgnoreCase("stop")) {
                            scrivendo = false;
                            System.out.println("Scrivendo false");
                        }
                    }
                    case "d" -> {

                    }
                }

            } while (true);
        } catch (IOException ex) {
            Logger.getLogger(Manage_peer.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void manda_connessione(Integer port_peer_connesso, String ip_peer_connesso) {
        this.ip_peer_connesso = ip_peer_connesso;
        this.port_peer_connesso = port_peer_connesso;
        try {
            client_socket = new Socket(ip_peer_connesso, port_peer_connesso);
            //qua devo mandare il mio indirizzo ip e la mia porta preceduti
            PrintWriter out = new PrintWriter(client_socket.getOutputStream(), true);
            out.println("c;" + ip + ";" + port + ";" + name);

        } catch (IOException ex) {
            Logger.getLogger(Manage_peer.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void manda_messaggi() {
        String str = "";
        scrivendo = true;
        do {
            try {
                Scanner s = new Scanner(System.in);
                client_socket = new Socket(ip_peer_connesso, port_peer_connesso);
                //qua devo mandare il mio indirizzo ip e la mia porta preceduti
                PrintWriter out = new PrintWriter(client_socket.getOutputStream(), true);
                System.out.println("[" + name + "]");
                str = s.nextLine();
                out.println("m;" + str);
                if (str.equals("stop")) {
                    scrivendo = false;
                    System.out.println("Scrivendo false");

                }

            } catch (IOException ex) {
                Logger.getLogger(Manage_peer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } while (scrivendo);
    }

    public void ricevi_messaggi() {

    }

}
