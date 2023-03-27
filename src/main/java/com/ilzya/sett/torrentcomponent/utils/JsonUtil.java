package com.ilzya.sett.torrentcomponent.utils;

import com.alibaba.fastjson.JSON;
import com.ilzya.sett.torrentcomponent.config.JsonConfig;
import com.ilzya.sett.torrentcomponent.entity.Rule;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class JsonUtil {

    public static List<Rule> readJson(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(JsonConfig.RULE_PATH));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();

            String jsonString = stringBuilder.toString();
            List<Rule> rules = JSON.parseArray(jsonString, Rule.class);
            return rules;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
