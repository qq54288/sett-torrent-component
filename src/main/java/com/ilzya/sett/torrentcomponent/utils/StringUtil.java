package com.ilzya.sett.torrentcomponent.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 字符串工具类
 *
 * @author sayokey
 * @date 2023/03/28
 */
@Slf4j
public class StringUtil {

    private static final String prefix = "${";
    private static final String suffix = "}";

    /**
     * 模板替换
     * @param vars
     * @param template
     * @return
     */
    public static String replaceVar(Map<String,String> vars, String template){
        if(!StringUtils.hasLength(template)){
            log.info(String.format("调用%s方法失败,模板字符串替换失败,模板字符串不能为空",
                    Thread.currentThread().getStackTrace()[1].getMethodName()));
            return null;
        }
        if(MapUtils.isEmpty(vars)){
            log.info(String.format("调用%s方法失败,模板字符串替换失败,map不能为空",
                    Thread.currentThread().getStackTrace()[1].getMethodName()));
            return null;
        }
        List<String> tempStrs = vars.keySet().stream().map(s -> prefix + s + suffix).
                collect(Collectors.toList());
        tempStrs.forEach(t->{
//            if(!template.contains(t)){
//                throw new RuntimeException(String.format("调用%s方法失败,模板字符串替换失败,map的key必须存在于模板字符串中",
//                        Thread.currentThread().getStackTrace()[1].getMethodName()));
//            }
        });
        StringSubstitutor stringSubstitutor = new StringSubstitutor(vars);
        return stringSubstitutor.replace(template);
    }

    /**
     * base64编码
     * @param str
     * @return
     */
    public static String encodeBase64(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes());
    }


    /**
     * 如果字符串被编码则解码
     *
     * @param str str
     * @return {@link String}
     */
    public static String decodeIfEncoded(String str) {
        try {
            return URLDecoder.decode(str, StandardCharsets.UTF_8.toString());
        } catch (IllegalArgumentException | UnsupportedEncodingException e) {
            return str;
        }
    }

}
