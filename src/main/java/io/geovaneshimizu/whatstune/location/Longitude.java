package io.geovaneshimizu.whatstune.location;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;

public class Longitude implements Serializable, Comparable<Longitude> {

    private static final long serialVersionUID = 1786675573152313158L;

    private final double value;

    private Longitude(double value) {
        if (value < -180.0 || value >= 180.0) {
            throw new IllegalArgumentException("Invalid longitude value");
        }
        this.value = value;
    }

    public static Longitude of(String value) {
        return new Longitude(Double.parseDouble(value));
    }

    public double asDouble() {
        return value;
    }

    @Override
    public int compareTo(@NotNull Longitude longitude) {
        return Double.compare(this.value, longitude.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Longitude latitude = (Longitude) o;
        return Double.compare(latitude.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Longitude{" +
                "value=" + value +
                '}';
    }
}
