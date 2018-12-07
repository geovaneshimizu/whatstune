package io.geovaneshimizu.whatstune.music;

import java.io.IOException;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonGenerator;

import io.geovaneshimizu.whatstune.serialization.JsonSerializable;

class Track implements JsonSerializable {

    private final String title;

    private Track(String title) {
        this.title = title;
    }

    static Track withTitle(String title) {
        return new Track(title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return Objects.equals(title, track.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return "Track{" +
                "title='" + title + '\'' +
                '}';
    }

    @Override
    public void writeJson(JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStringField("title", title);
    }
}
