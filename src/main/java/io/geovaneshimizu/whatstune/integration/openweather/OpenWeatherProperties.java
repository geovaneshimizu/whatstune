package io.geovaneshimizu.whatstune.integration.openweather;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("whatstune.integration.openweather")
class OpenWeatherProperties {

    private String apiKey;

    private OpenWeatherProperties() {
        // Properties holder
    }

    String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
