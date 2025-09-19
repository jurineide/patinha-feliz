package com.site.patinha_feliz.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI patinhaFelizOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Patinha Feliz 🐾")
                        .description("Documentação da API para gerenciamento de usuários e postagens")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Equipe Patinha Feliz")
                                .email("contato@patinhafeliz.com"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Repositório no GitHub")
                        .url("https://github.com/seu-repo"));
    }
}
