package com.ilzya.sett.torrentcomponent.utils;

import com.ilzya.sett.torrentcomponent.entity.Proxy;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author sayokey
 */
public class HttpUtil {

    /**
     * 发送GET请求
     * @param URL 请求地址
     * @param proxyM 代理
     * @return
     * @throws Exception
     */
    public static String toGet(String URL, Proxy proxyM) throws Exception{
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        if(proxyM.isEnable()){
            java.net.Proxy proxy = new java.net.Proxy(java.net.Proxy.Type.HTTP, new java.net.InetSocketAddress(proxyM.getHost(),proxyM.getPort()));
            builder.proxy(proxy);
        }
        OkHttpClient client = builder.build();
        Request request = new Request.Builder()
                .url(URL)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


}
