/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat_peer;

/**
 *
 * @author ricci_marco
 */
public class PeerConnesso {

    private Boolean connesso;
    private String ip_peer_connesso;
    private Integer port_peer_connesso;

    public PeerConnesso() {
        connesso = false;
    }

    public PeerConnesso(String ip_peer_connesso, Integer port_peer_connesso) {
        connesso = true;
        this.ip_peer_connesso = ip_peer_connesso;
        this.port_peer_connesso = port_peer_connesso;

    }

    public Boolean getConnesso() {
        return connesso;
    }

    public void setConnesso(Boolean connesso) {
        this.connesso = connesso;
    }

    public String getIp_peer_connesso() {
        return ip_peer_connesso;
    }

    public Integer getPort_peer_connesso() {
        return port_peer_connesso;
    }
    
    

}
