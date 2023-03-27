package com.ilzya.sett.torrentcomponent.entity;

import lombok.Data;

@Data
public class Proxy {

    /**
     * 主机
     */
    String host;

    /**
     * 端口
     */
    Integer port;

    /**
     * 是否启用
     */
    boolean enable;

}
