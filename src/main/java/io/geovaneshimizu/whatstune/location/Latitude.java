package io.geovaneshimizu.whatstune.location;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;

public class Latitude implements Serializable, Comparable<Latitude> {

    private static final long serialVersionUID = -4203203093728449635L;

    private final double value;

    private Latitude(double value) {
        if (value < -90.0 || value > 90.0) {
            throw new IllegalArgumentException("Invalid latitude value");
        }
        this.value = value;
    }

    public static Latitude of(String value) {
        return new Latitude(Double.parseDouble(value));
    }

    public double asDouble() {
        return value;
    }

    @Override
    public int compareTo(@NotNull Latitude latitude) {
        return Double.compare(this.value, latitude.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Latitude latitude = (Latitude) o;
        return Double.compare(latitude.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Latitude{" +
                "value=" + value +
                '}';
    }
}
