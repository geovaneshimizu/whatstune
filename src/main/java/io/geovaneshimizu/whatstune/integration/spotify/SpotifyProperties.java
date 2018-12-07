package io.geovaneshimizu.whatstune.integration.spotify;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("whatstune.integration.spotify")
class SpotifyProperties {

    private String clientId;

    private String clientSecret;

    private SpotifyProperties() {
        // Properties holder
    }

    String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
