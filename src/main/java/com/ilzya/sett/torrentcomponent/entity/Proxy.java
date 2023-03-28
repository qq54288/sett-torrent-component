package com.ilzya.sett.torrentcomponent.entity;

import lombok.Data;

/**
 * 代理
 *
 * @author sayokey
 * @date 2023/03/28
 */
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
