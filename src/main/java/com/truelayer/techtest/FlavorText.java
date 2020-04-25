package com.truelayer.techtest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Data class which holds "Flavor text" i.e. a description of a pokemon in a particular flavor.
 * Each pokemon will have many flavours
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlavorText {

    private final String flavorText;
    private final Language language;

    public FlavorText(@JsonProperty("flavor_text") String flavorText, @JsonProperty("language") Language language){
        this.flavorText = flavorText;
        this.language = language;
    }

    public String getFlavorText() {
        return flavorText;
    }

    public Language getLanguage() {
        return language;
    }

    @JsonIgnore
    public String getLanguageName(){
        return language.getName();
    }
}
