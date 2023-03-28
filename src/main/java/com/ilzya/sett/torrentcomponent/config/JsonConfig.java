package com.ilzya.sett.torrentcomponent.config;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JsonConfig {

    public static String RULE_PATH;

    @Value("${sett.torrent.rule.path}")
    public void setRulePath(String path) {
        if (Strings.isBlank(path)){
            throw new IllegalArgumentException("请配置sett.torrent.rule.path");
        }
        RULE_PATH = path;
    }





}
