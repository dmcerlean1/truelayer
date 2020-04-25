package com.truelayer.techtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest
@AutoConfigureMockRestServiceServer
public class PokemonGetterTest {

    @Autowired
    PokemonGetter pokemonGetter;

    @Autowired
    MockRestServiceServer server;

    @Autowired
    ObjectMapper objectMapper;

    private static final String POKEMON_NAME = "Charizard";
    private static final String POKEMON_DESC = "The pokemon description";

    @Test
    public void get_whenPokemonExists_returnPokemon() throws Exception {
        var flavorText = new FlavorTextEntries(
                List.of(new FlavorText(POKEMON_DESC, new Language("en"))));
        server.expect(requestTo(StringContains.containsString("pokeapi.co/api/v2/pokemon-species/")))
                .andRespond(withSuccess(objectMapper.writeValueAsString(flavorText), MediaType.APPLICATION_JSON));

        var returnedPokemon = pokemonGetter.get(POKEMON_NAME);
        assertThat(returnedPokemon, is(Optional.of(new Pokemon(POKEMON_NAME, POKEMON_DESC))));
    }

    @Test
    public void get_whenResponseIsNull_returnEmptyOptional() throws Exception {
        server.expect(requestTo(StringContains.containsString("pokeapi.co/api/v2/pokemon-species/")))
                .andRespond(withStatus(HttpStatus.NO_CONTENT));

        var returnedPokemon = pokemonGetter.get(POKEMON_NAME);
        assertThat(returnedPokemon, is(Optional.empty()));
    }

    @Test
    public void get_whenPokemonDoesntExist_returnEmptyOptional() throws Exception {
        server.expect(requestTo(StringContains.containsString("pokeapi.co/api/v2/pokemon-species/")))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        var returnedPokemon = pokemonGetter.get(POKEMON_NAME);
        assertThat(returnedPokemon, is(Optional.empty()));
    }

    @Test
    public void get_whenLanguageIsNotEnglish_returnEmptyOptional() throws Exception {
        var flavorText = new FlavorTextEntries(
                List.of(new FlavorText(POKEMON_DESC, new Language("ja"))));
        server.expect(requestTo(StringContains.containsString("pokeapi.co/api/v2/pokemon-species/")))
                .andRespond(withSuccess(objectMapper.writeValueAsString(flavorText), MediaType.APPLICATION_JSON));

        var returnedPokemon = pokemonGetter.get(POKEMON_NAME);
        assertThat(returnedPokemon, is(Optional.empty()));
    }
}
