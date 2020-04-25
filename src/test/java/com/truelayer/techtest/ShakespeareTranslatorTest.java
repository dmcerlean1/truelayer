package com.truelayer.techtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(ShakespeareTranslator.class)
class ShakespeareTranslatorTest {

    String TRANSLATED = "'t translated text";
    String TEXT = "the text to translate";
    String TRANSLATION = "language translated to";
    String ENDPOINT = "api.funtranslations.com/translate/shakespeare";

    @Autowired
    ShakespeareTranslator translator;

    @Autowired
    MockRestServiceServer server;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void get_whenToTranslateIsBlank_returnsEmptyOptional(){
        assertThat(translator.get(""), is(Optional.empty()));
    }

    @Test
    public void get_whenPokemonExists_returnPokemon() throws Exception {
        var response = new ShakespeareResponse(new Translation(TRANSLATED, TRANSLATION, TEXT));
        server.expect(requestTo(StringContains.containsString(ENDPOINT)))
                .andRespond(withSuccess(objectMapper.writeValueAsString(response), MediaType.APPLICATION_JSON));

        var translation = translator.get(TEXT);
        assertThat(translation, is(Optional.of(new Translation(TRANSLATED, TRANSLATION, TEXT))));
    }

    @Test
    public void get_whenRequestThrowsException_returnEmptyOptional() throws Exception {
        server.expect(requestTo(StringContains.containsString(ENDPOINT)))
                .andRespond(withServerError());
        
        var translation = translator.get(TEXT);
        assertThat(translation, is(Optional.empty()));
    }

    @Test
    public void get_whenRequestIsNull_throwsNPE(){
        assertThrows(NullPointerException.class, () -> translator.get(null));
    }
}