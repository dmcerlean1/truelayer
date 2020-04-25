package com.truelayer.techtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * Class to retrieve a Pokemon using the PokeApi
 */
@Service
public class PokemonGetter {

    Logger logger = LoggerFactory.getLogger(PokemonGetter.class);

    private static final String pokeApi = "https://pokeapi.co/api/v2/pokemon-species/%s";

    private final RestTemplate restTemplate;

    public PokemonGetter(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }

    /**
     * Get a pokemon from the pokeAPI using the supplied rest remplate
     * @param pokemonName - the name of the pokemon to fetch
     * @return an Optional containing a pokemon, or an empty Optional if the Pokemon
     * doesn't exist
     */
    public Optional<Pokemon> get(String pokemonName)
    {
        var params = String.format(pokeApi, pokemonName);
        try {
            var other = restTemplate.getForObject(params, FlavorTextEntries.class);
            if(other == null){
                return Optional.empty();
            }
            return other.getFlavorTextEntries()
                    .stream()
                    .filter(f -> f.getLanguageName().equals("en"))
                    .map(ft -> new Pokemon(pokemonName, ft.getFlavorText()))
                    .findFirst();
        } catch (RestClientException ex) {
            logger.debug("PokeApi returned 404 for {}", pokemonName);
        }
        return Optional.empty();
    }
}
