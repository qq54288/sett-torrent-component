package com.ilzya.sett.torrentcomponent.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置
 *
 * @author sayokey
 * @date 2023/03/29
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private ApiInfo magnetApiInfo(){
        return new ApiInfoBuilder()
                .title("sett-torrent-component-apiDoc")
                .description("组件API文档")
                .version("1.0")
                .contact(new Contact("sayokey","https://github.com/sayokey/sett-torrent-component","2044186427@qq.com"))
                .build();
    }

    @Bean
    public Docket mangnetApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(magnetApiInfo())
                .groupName("magnet")
                .select()
                .paths(Predicates.and(PathSelectors.regex("/magnet/.*")))
                .build();
    }

    @Bean
    public Docket thunderApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(magnetApiInfo())
                .groupName("thunder")
                .select()
                .paths(Predicates.and(PathSelectors.regex("/thunder/.*")))
                .build();
    }

}
