package com.truelayer.techtest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PokemonController {

    private final PokemonGetter pokemonGetter;

    public PokemonController(PokemonGetter pokemonGetter){
        this.pokemonGetter = pokemonGetter;
    }

    @GetMapping("/pokemon/{name}")
    Pokemon getShakespearean(@PathVariable("name") String pokemonName) {
        return pokemonGetter.get(pokemonName).get();
    }
}
