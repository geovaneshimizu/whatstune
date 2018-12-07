package io.geovaneshimizu.whatstune.integration.spotify;

import java.util.Objects;

import com.wrapper.spotify.SpotifyApi;

@FunctionalInterface
public interface SpotifyApiCall<T extends SpotifyApi, R, E extends Exception> {

    R call(SpotifyApi api) throws E;

    default SpotifyApiCall<SpotifyApi, R, Exception> intercept(SpotifyApiCall<SpotifyApi, ? extends T, Exception> before) {
        Objects.requireNonNull(before);
        return api -> this.call(before.call(api));
    }
}
