package com.ilzya.sett.torrentcomponent.config;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * bt客户端配置
 *
 * @author sayokey
 * @date 2023/03/28
 */
@Component
public class BtClientConfig {


    public static String DOWNLOAD_PATH;


    @Value("${sett.torrent.download.path}")
    public void setDownloadPath(String path) {
        if (Strings.isBlank(path)){
            throw new IllegalArgumentException("请配置sett.torrent.download.path");
        }
        DOWNLOAD_PATH = path;
    }

}
