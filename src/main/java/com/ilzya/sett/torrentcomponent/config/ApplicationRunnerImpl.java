package com.ilzya.sett.torrentcomponent.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 应用程序启动完成impl
 *
 * @author sayokey
 * @date 2023/03/28
 */
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    @Value("${server.port}")
    private int port;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("sett-turrent-component 启动成功");
        System.out.println("sett-turrent-component 端口号：" + port);
        System.out.println(String.format("Github:%s，如果觉得不错可以star一下","https://github.com/sayokey/sett-torrent-component"));
        System.out.println(String.format("Swagger Api Doc : %s","http://localhost:"+port+"/swagger-ui.html"));
    }
}
