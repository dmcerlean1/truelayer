package com.truelayer.techtest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FlavorTextEntries {

    private final List<FlavorText> flavorTextEntries;

    public FlavorTextEntries(@JsonProperty("flavor_text_entries") List<FlavorText> flavorTextEntries){
        this.flavorTextEntries = flavorTextEntries;
    }

    public List<FlavorText> getFlavorTextEntries() {
        return flavorTextEntries;
    }

}
