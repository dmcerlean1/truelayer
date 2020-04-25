package com.truelayer.techtest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShakespeareResponse {

    private final Translation translation;

    public ShakespeareResponse(@JsonProperty("contents") Translation translation){
        this.translation = translation;
    }

    public Translation getTranslation() {
        return translation;
    }
}
