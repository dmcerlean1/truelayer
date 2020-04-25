package com.truelayer.techtest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * A translation i.e. some translated text and the language it was translated to
 */
public class Translation {

    /** the text that was translated */
    private final String translated;
    /** the language that the text was translated to */
    private final String translation;
    /** the translated text */
    private final String text;

    public Translation(@JsonProperty("translated") String translated,
                       @JsonProperty("translation") String translation,
                       @JsonProperty("text") String text) {
        this.translated = translated;
        this.translation = translation;
        this.text = text;
    }

    public String getTranslated() {
        return translated;
    }

    public String getTranslation() {
        return translation;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Translation that = (Translation) o;
        return Objects.equals(translated, that.translated) &&
                Objects.equals(translation, that.translation) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(translated, translation, text);
    }
}
