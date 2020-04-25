package com.truelayer.techtest;

import java.util.Objects;

public class Pokemon {

    private final String name;
    private final String description;

    public Pokemon(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return Objects.equals(name, pokemon.name) &&
                Objects.equals(description, pokemon.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}
