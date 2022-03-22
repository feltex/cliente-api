package br.com.feltex.clienteapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ServerBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket publicApi() {

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder()
                        .description("API de acesso aos dados de clientes")
                        .title("API de Clientes")
                        .version("3.0.1")
                        .build())
                .host("clienteUI")
                .servers(new ServerBuilder()
                                .url("http://cliente-api")
                                .build(),
                        new ServerBuilder()
                                .url("http://localhost:8080")
                                .build(),
                        new ServerBuilder()
                                .url("http://feltex.com.br")
                                .build());

    }
}
