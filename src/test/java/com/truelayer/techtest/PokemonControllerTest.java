package com.truelayer.techtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(PokemonController.class)
public class PokemonControllerTest {

    String NAME = "Snorlax";
    String DESCRIPTION = "Is Sleeping";
    String TRANSLATED = "Translated text";

    @Autowired
    MockMvc mvc;

    @MockBean
    PokemonGetter pokemonGetter;

    @MockBean
    ShakespeareTranslator translator;

    @Test
    public void getShakespearean_whenPokemonIsFound_returnsTranslatedText() throws Exception {
        Pokemon pokemon = new Pokemon(NAME, DESCRIPTION);
        Translation translation = new Translation(TRANSLATED, "", "");
        when(pokemonGetter.get(any())).thenReturn(Optional.of(pokemon));
        when(translator.get(any())).thenReturn(Optional.of(translation));
        mvc.perform(get("/pokemon/" + NAME))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(TRANSLATED)));
    }

    @Test
    public void getShakespearean_whenPokemonCantBeFound_returns404() throws Exception {
        when(pokemonGetter.get(any())).thenReturn(Optional.empty());
        mvc.perform(get("/pokemon/" + NAME))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getShakespearean_whenPokkjjkemonCantBeFound_returns404() throws Exception {
        Pokemon pokemon = new Pokemon(NAME, DESCRIPTION);
        when(pokemonGetter.get(any())).thenReturn(Optional.of(pokemon));
        when(translator.get(any())).thenReturn(Optional.empty());
        mvc.perform(get("/pokemon/" + NAME))
                .andDo(print())
                .andExpect(status().is5xxServerError());
    }
}
