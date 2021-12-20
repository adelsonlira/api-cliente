package com.apicliente.apicliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ApiClienteApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiClienteApplication.class, args);
    }

}
