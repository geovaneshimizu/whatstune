package io.geovaneshimizu.whatstune.integration.spotify;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

import java.io.IOException;
import java.time.Instant;

import org.slf4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;

@Component
@Scope(SCOPE_PROTOTYPE)
class SpotifyApiAccessTokenHandler {

    private final Logger logger;

    private Instant tokenExpiration;

    SpotifyApiAccessTokenHandler(Logger logger) {
        this.logger = logger;
        this.tokenExpiration = Instant.now().minusSeconds(5L);
    }

    SpotifyApi getAccessTokenIfNecessary(SpotifyApi spotifyApi) throws IOException, SpotifyWebApiException {
        if (Instant.now().isAfter(tokenExpiration)) {
            logger.debug("Token has expired, getting new one...");
            ClientCredentials clientCredentials =
                    spotifyApi.clientCredentials()
                            .build()
                            .execute();

            tokenExpiration = Instant.now().plusSeconds(clientCredentials.getExpiresIn()).minusSeconds(5L);

            spotifyApi.setAccessToken(clientCredentials.getAccessToken());
        }
        return spotifyApi;
    }
}
