package com.masprogtechs.sales.application.system.config;


import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sistema de Gestão de Vendas - API REST")
                        .version("v1")
                        .description("RESTfull API - Sistema de Gestão de Vendas")
                        .termsOfService("https://about-mauro.netlify.app/")
                        .license(
                                new License()
                                        .name("GNU GENERAL PUBLIC LICENSE")
                                        .url("https://www.gnu.org/licenses/gpl-3.0.pt-br.html")
                        )
                );
    }
}
