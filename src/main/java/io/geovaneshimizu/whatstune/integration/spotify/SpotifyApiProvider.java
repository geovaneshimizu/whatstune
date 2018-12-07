package io.geovaneshimizu.whatstune.integration.spotify;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wrapper.spotify.SpotifyApi;

@Configuration
class SpotifyApiProvider {

    @Bean
    SpotifyApi spotifyApi(SpotifyProperties properties) {
        return new SpotifyApi.Builder()
                .setClientId(properties.getClientId())
                .setClientSecret(properties.getClientSecret())
                .build();
    }
}
