package com.truelayer.techtest;

import java.util.Optional;

/**
 * Get a resource from an external API
 * @param <T>
 */
public interface ExternalGetter<T> {

    /**
     * Fetch
     * @param toGet
     * @return
     */
    Optional<T> get(String toGet);
}
