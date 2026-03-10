package com.lewchu.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("我的接口文档")
                        .contact(new Contact().name("lewchu").url("https://github.com/lewchu").email("lewchu@gmail.com"))
                        .description("这是我的swaggerui生成的接口文档")
                        .version("1.0"));
    }
}
