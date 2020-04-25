package com.truelayer.techtest;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class to help with deserialisation of poke-api
 */
public class Language {

    private final String name;

    public Language(@JsonProperty("name") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
