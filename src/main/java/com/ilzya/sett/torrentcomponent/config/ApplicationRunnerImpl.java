package com.ilzya.sett.torrentcomponent.config;

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
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("sett-turrent-component 启动成功");
    }
}
