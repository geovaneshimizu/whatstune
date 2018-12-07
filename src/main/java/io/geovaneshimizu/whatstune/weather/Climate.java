package io.geovaneshimizu.whatstune.weather;

import static io.geovaneshimizu.whatstune.weather.Scale.CELSIUS;

import java.util.Arrays;
import java.util.function.Predicate;

public enum Climate implements Predicate<Weather> {

    HOT {
        @Override
        public boolean test(Weather weather) {
            return weather.isTemperature(t -> t.greaterThan(Temperature.of(30d, CELSIUS)));
        }
    },

    COOL {
        @Override
        public boolean test(Weather weather) {
            return weather.isTemperature(t ->
                    TemperatureRange
                            .between(Temperature.of(15d, CELSIUS),
                                    Temperature.of(30d, CELSIUS))
                            .includes(t));
        }
    },

    CHILLY {
        @Override
        public boolean test(Weather weather) {
            return weather.isTemperature(t ->
                    TemperatureRange
                            .between(Temperature.of(10d, CELSIUS),
                                    Temperature.of(14d, CELSIUS))
                            .includes(t));
        }
    },

    FREEZING {
        @Override
        public boolean test(Weather weather) {
            return weather.isTemperature(t -> t.lessThan(Temperature.of(10d, CELSIUS)));
        }
    },

    UNDEFINED {
        @Override
        public boolean test(Weather weather) {
            return false;
        }
    };

    public static Climate basedOn(Weather weather) {
        return Arrays.stream(values())
                .filter(climate -> climate.test(weather))
                .findFirst()
                .orElse(UNDEFINED);
    }
}
