package com.truelayer.techtest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PokemonController {

    private final ExternalGetter<Pokemon> pokemonGetter;
    private final ExternalGetter<Translation> translator;

    public PokemonController(PokemonGetter pokemonGetter, ShakespeareTranslator translator){
        this.pokemonGetter = pokemonGetter;
        this.translator = translator;
    }

    @GetMapping("/pokemon/{name}")
    Pokemon getShakespearean(@PathVariable("name") String pokemonName) {
        Pokemon pokemon = pokemonGetter.get(pokemonName)
                .orElseThrow(PokemonNotFound::new);
        Translation translatedDescription = translator.get(pokemon.getDescription())
                .orElseThrow(TranslationFailed::new);
        return new Pokemon(pokemonName, translatedDescription.getTranslated());
    }
}
