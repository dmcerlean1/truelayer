package com.truelayer.techtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PokemonTranslator {

    private static final Logger log = LoggerFactory.getLogger(PokemonTranslator.class);

    public static void main(String[] args) {
        SpringApplication.run(PokemonTranslator.class, args);
        log.info("Started PokemonTranslator");
    }

    @Bean
    ShakespeareApi getShakespeareApiKey(){
        return new ShakespeareApi();
    }
}
