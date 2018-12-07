package io.geovaneshimizu.whatstune.music;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

import com.fasterxml.jackson.core.JsonGenerator;

import io.geovaneshimizu.whatstune.serialization.JsonSerializable;

class Playlist implements JsonSerializable {

    private final List<Track> tracks;

    Playlist() {
        this.tracks = new ArrayList<>();
    }

    static Collector<Track, Playlist, Playlist> create() {
        return Collector.of(Playlist::new, Playlist::add, Playlist::addAll);
    }

    Playlist add(Track track) {
        tracks.add(track);
        return this;
    }

    Playlist addAll(Playlist playlist) {
        tracks.addAll(playlist.tracks);
        return this;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "tracks=" + tracks +
                '}';
    }

    @Override
    public void writeJson(JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeArrayFieldStart("tracks");
        for (Track track : tracks) {
            jsonGenerator.writeStartObject();
            track.writeJson(jsonGenerator);
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
    }
}
