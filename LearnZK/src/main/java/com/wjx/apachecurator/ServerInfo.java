package com.wjx.apachecurator;

import java.io.Serializable;

/**
 * @Author WangJX
 * @Date 2022/5/24 10:03
 * @Description
 */
public class ServerInfo implements Serializable {

    private String host;

    private int port;

    public ServerInfo() {
    }

    public ServerInfo(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
