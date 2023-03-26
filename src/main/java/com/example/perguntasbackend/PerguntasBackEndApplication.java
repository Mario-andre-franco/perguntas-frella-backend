package com.example.perguntasbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class PerguntasBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(PerguntasBackEndApplication.class, args);
    }

}
