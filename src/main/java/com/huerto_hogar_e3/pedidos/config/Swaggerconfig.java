package com.huerto_hogar_e3.pedidos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class Swaggerconfig {
    @Bean
    public OpenAPI customerApi(){
        return new OpenAPI()
        .info(new Info()
        .title("API huerto hogar pedido")
        .version("1.0.0")
        .description("API documentacion para huerto hogar pedido")
        );
    }
}
