package io.geovaneshimizu.whatstune.weather;

import java.util.Optional;

import io.geovaneshimizu.whatstune.location.CityName;
import io.geovaneshimizu.whatstune.location.GeoCoordinates;

public interface WeatherService {

    Optional<Weather> currentWeatherByCityName(CityName city);

    Optional<Weather> currentWeatherByCoordinates(GeoCoordinates coordinates);
}
