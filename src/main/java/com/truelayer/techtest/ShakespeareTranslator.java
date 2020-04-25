package com.truelayer.techtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.HttpMethod.POST;

/**
 * Translates text to Shakespearian English
 */
@Service
public class ShakespeareTranslator implements ExternalGetter<Translation> {

    private static final Logger logger = LoggerFactory.getLogger(PokemonGetter.class);

    private static final String ENDPOINT = "https://api.funtranslations.com/translate/shakespeare.json";

    private final RestTemplate restTemplate;
    private HttpHeaders headers;

    public ShakespeareTranslator(RestTemplateBuilder restTemplateBuilder, ShakespeareApi apiInfo){
        this.restTemplate = restTemplateBuilder.build();
        configureHeader(apiInfo.getApiKey());
    }

    /**
     * Translate a String to Shakespearian English
     * @param toTranslate - the String to translate. Must not be null or blank.
     * @return translated String
     */
    public Optional<Translation> get(String toTranslate){
        Objects.requireNonNull(toTranslate);
        if(toTranslate.equals("")) {
            return Optional.empty();
        }
        var entity = new HttpEntity<>(Map.of("text", toTranslate), headers);
        try {
            var response = restTemplate.exchange(ENDPOINT, POST, entity, ShakespeareResponse.class);
            return response.hasBody() ? Optional.of(response.getBody().getTranslation()) : Optional.empty();
        } catch (RestClientException ex) {
            logger.debug("Shakespeare Translator failed to translate text \"{}\"", toTranslate);
        }
        return Optional.empty();
    }

    private void configureHeader(String apiKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Funtranslations-Api-Secret", apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        this.headers = headers;
    }
}
