package com.ilzya.sett.torrentcomponent.utils;

import com.ilzya.sett.torrentcomponent.entity.Proxy;
import okhttp3.*;


/**
 * http工具类
 *
 * @author sayokey
 * @date 2023/03/28
 */
public class HttpUtil {

    /**
     * 发送GET请求
     * @param uRL 请求地址
     * @param proxyM 代理
     * @return
     * @throws Exception
     */
    public static String toGet(String uRL, Proxy proxyM) throws Exception{
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        if(proxyM.isEnable()){
            java.net.Proxy proxy = new java.net.Proxy(java.net.Proxy.Type.HTTP, new java.net.InetSocketAddress(proxyM.getHost(),proxyM.getPort()));
            builder.proxy(proxy);
        }
        OkHttpClient client = builder.build();
        Request request = new Request.Builder()
                .url(uRL)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


}
