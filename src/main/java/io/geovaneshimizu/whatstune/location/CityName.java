package io.geovaneshimizu.whatstune.location;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;

public class CityName implements Serializable, Comparable<CityName> {

    private static final long serialVersionUID = -8478979936878279937L;

    private final String value;

    private CityName(String value) {
        Objects.requireNonNull(value, "City name must not be null");
        if (value.isEmpty()) {
            throw new IllegalArgumentException("Invalid city name: empty value");
        }
        this.value = value;
    }

    public static CityName of(String value) {
        return new CityName(value);
    }

    public String asString() {
        return value;
    }

    @Override
    public int compareTo(@NotNull CityName cityName) {
        return this.value.compareTo(cityName.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityName cityName = (CityName) o;
        return Objects.equals(value, cityName.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "CityName{" +
                "value='" + value + '\'' +
                '}';
    }
}
