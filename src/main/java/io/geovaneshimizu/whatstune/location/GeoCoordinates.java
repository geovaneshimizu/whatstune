package io.geovaneshimizu.whatstune.location;

import java.util.Objects;

public class GeoCoordinates {

    private final Latitude latitude;

    private final Longitude longitude;

    private GeoCoordinates(Latitude latitude,
                           Longitude longitude) {
        Objects.requireNonNull(latitude, "Latitude must not be null");
        Objects.requireNonNull(longitude, "Longitude must not be null");
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static LatitudeCoordinate withLatitude(Latitude latitude) {
        return longitude -> new GeoCoordinates(latitude, longitude);
    }

    public Latitude latitude() {
        return latitude;
    }

    public Longitude longitude() {
        return longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeoCoordinates that = (GeoCoordinates) o;
        return Objects.equals(latitude, that.latitude) &&
                Objects.equals(longitude, that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    @Override
    public String toString() {
        return "GeoCoordinates{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @FunctionalInterface
    public interface LatitudeCoordinate {

        GeoCoordinates andLongitude(Longitude longitude);
    }
}
