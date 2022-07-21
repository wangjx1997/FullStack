package com.wjx.apachecurator;

/**
 * @Author WangJX
 * @Date 2022/5/24 10:04
 * @Description
 */
public class Config {
    private String path;
    private String host;
    private int port;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public String getHostPort() {
        return host + ":" + port;
    }
}
