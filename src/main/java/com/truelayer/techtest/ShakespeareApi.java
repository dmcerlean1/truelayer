package com.truelayer.techtest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;

import javax.annotation.Resource;

/**
 * Resource class that holds the API key for the Fun Translations Shakespeare API
 */
@Resource
public class ShakespeareApi {
    @Value("${shakespeare-api-key}")
    @NonNull
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }
}
