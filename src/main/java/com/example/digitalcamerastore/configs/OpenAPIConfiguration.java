package com.example.digitalcamerastore.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:9998");
        server.setDescription("Management REST API Documentation");

        Info information = new Info()
                .title("Management REST API Documentation")
                .version("1.0")
                .description("This API exposes endpoints to manage DigitalCamera.");

        return new OpenAPI().info(information).servers(List.of(server));
    }
}
