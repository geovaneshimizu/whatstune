package io.geovaneshimizu.whatstune.weather;

import io.geovaneshimizu.whatstune.location.CityName;
import io.geovaneshimizu.whatstune.location.GeoCoordinates;

public interface WeatherService {

    Weather currentWeatherByCityName(CityName city);

    Weather currentWeatherByCoordinates(GeoCoordinates coordinates);
}
