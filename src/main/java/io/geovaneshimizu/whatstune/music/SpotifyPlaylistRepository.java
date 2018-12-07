package io.geovaneshimizu.whatstune.music;

import java.util.Arrays;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;

import io.geovaneshimizu.whatstune.integration.spotify.SpotifyClient;

@Repository
class SpotifyPlaylistRepository implements PlaylistRepository {

    private final SpotifyClient spotifyClient;

    private final Logger logger;

    SpotifyPlaylistRepository(SpotifyClient spotifyClient,
                              Logger logger) {
        this.spotifyClient = spotifyClient;
        this.logger = logger;
    }

    @Override
    public Playlist findByCategory(Category category) {
        try {
            Paging<PlaylistSimplified> playlistSimplified =
                    spotifyClient.call(api -> api.getCategorysPlaylists(category.name().toLowerCase()).build().execute());

            Stream<PlaylistTrack> playlistTrackStream =
                    Arrays.stream(playlistSimplified.getItems())
                            .findFirst()
                            .map(PlaylistSimplified::getId)
                            .map(this::fetchPlaylistTracks)
                            .orElseGet(Stream::empty);

            return playlistTrackStream
                    .map(PlaylistTrack::getTrack)
                    .map(com.wrapper.spotify.model_objects.specification.Track::getName)
                    .map(Track::withTitle)
                    .collect(Playlist.create());
        } catch (Exception e) {
            return recoveryPlaylist(e);
        }
    }

    private Stream<PlaylistTrack> fetchPlaylistTracks(String playlistId) {
        Paging<PlaylistTrack> playlistTrack =
                spotifyClient.call(api -> api.getPlaylistsTracks(playlistId).build().execute());
        return Arrays.stream(playlistTrack.getItems())
                .limit(16);
    }

    private Playlist recoveryPlaylist(Throwable throwable) {
        logger.warn("Recovering from {}. Returning default playlist", throwable.getMessage());
        return Stream.of(Track.withTitle("Master of Puppets"),
                Track.withTitle("Back in Black"),
                Track.withTitle("Paranoid"),
                Track.withTitle("Smoke on the Water"),
                Track.withTitle("Immigrant Song"))
                .collect(Playlist.create());
    }
}
