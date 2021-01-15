package com.example.demo.config;

import com.example.demo.listener.ClientDriverListener;
import com.opcooc.storage.provider.YmlClientDriverProvider;
import io.swagger.annotations.Api;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class DemoConfiguration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(ApiInfo.DEFAULT)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public ClientDriverListener clientDriverListener() {
        return new ClientDriverListener();
    }

    @Bean
    public DemoClientDriverProvider demoClientDriverProvider() {
        return new DemoClientDriverProvider();
    }

    @Bean
    public DemoBucketConverter demoBucketConverter() {
        return new DemoBucketConverter();
    }

    @Bean
    public DemoObjectConverter demoObjectConverter() {
        return new DemoObjectConverter();
    }
}
