package io.geovaneshimizu.whatstune.weather;

import java.time.Instant;
import java.util.Objects;
import java.util.function.Predicate;

public class Weather {

    private final Temperature temperature;

    private final Instant when;

    private Weather(Temperature temperature,
                    Instant timestamp) {
        Objects.requireNonNull(temperature, "Temperature must not be null");
        Objects.requireNonNull(timestamp, "Timestamp must not be null");
        this.temperature = temperature;
        this.when = timestamp;
    }

    static Weather now(Temperature temperature) {
        return new Weather(temperature, Instant.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather = (Weather) o;
        return Objects.equals(temperature, weather.temperature) &&
                Objects.equals(when, weather.when);
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperature, when);
    }

    @Override
    public String toString() {
        return "Weather{" +
                "temperature=" + temperature +
                ", when=" + when +
                '}';
    }

    boolean isTemperature(Predicate<Temperature> condition) {
        return condition.test(temperature);
    }
}
