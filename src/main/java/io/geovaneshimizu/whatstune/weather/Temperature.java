package io.geovaneshimizu.whatstune.weather;

import java.util.Objects;

class Temperature implements Comparable<Temperature> {

    private final double value;

    private final Scale scale;

    private Temperature(double value,
                        Scale scale) {
        Objects.requireNonNull(scale, "Scale must not be null");
        if (value < scale.minimum() || value > scale.maximum()) {
            throw new IllegalArgumentException("Invalid value " + value + " for " + scale);
        }
        this.value = value;
        this.scale = scale;
    }

    static Temperature of(double value, Scale scale) {
        return new Temperature(value, scale);
    }

    @Override
    public int compareTo(Temperature other) {
        // TODO Temperatures must be on the same scale
        return Double.compare(this.value, other.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Temperature that = (Temperature) o;
        return Double.compare(that.value, value) == 0 &&
                scale == that.scale;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, scale);
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "value=" + value +
                ", scale=" + scale +
                '}';
    }

    boolean greaterThan(Temperature other) {
        return this.compareTo(other) > 0;
    }

    boolean lessThan(Temperature other) {
        return this.compareTo(other) < 0;
    }
}
