package io.geovaneshimizu.whatstune.weather;

import java.util.Objects;

class TemperatureRange {

    private final Temperature min;

    private final Temperature max;

    private TemperatureRange(Temperature min,
                             Temperature max) {
        Objects.requireNonNull(min, "Temperature min must not be null");
        Objects.requireNonNull(max, "Temperature max must not be null");
        this.min = min;
        this.max = max;
    }

    static TemperatureRange between(Temperature min,
                                    Temperature max) {
        return new TemperatureRange(min, max);
    }

    boolean includes(Temperature temperature) {
        return temperature.compareTo(min) >= 0
                && temperature.compareTo(max) <= 0;
    }
}
