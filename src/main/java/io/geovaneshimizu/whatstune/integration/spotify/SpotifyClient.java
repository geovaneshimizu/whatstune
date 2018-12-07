package io.geovaneshimizu.whatstune.integration.spotify;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.wrapper.spotify.SpotifyApi;

@Component
public class SpotifyClient {

    private final SpotifyApi spotifyApi;

    private final SpotifyApiAccessTokenHandler tokenHandler;

    private final Logger logger;

    SpotifyClient(SpotifyApi spotifyApi,
                  SpotifyApiAccessTokenHandler tokenHandler,
                  Logger logger) {
        this.spotifyApi = spotifyApi;
        this.tokenHandler = tokenHandler;
        this.logger = logger;
    }

    public <T> T call(SpotifyApiCall<SpotifyApi, T, Exception> caller) {
        try {
            return caller
                    .intercept(tokenHandler::getAccessTokenIfNecessary)
                    .call(spotifyApi);
        } catch (Exception e) {
            logger.error("Error while calling Spotify API: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
