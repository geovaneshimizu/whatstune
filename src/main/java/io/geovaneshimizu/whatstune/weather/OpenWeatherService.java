package io.geovaneshimizu.whatstune.weather;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import io.geovaneshimizu.whatstune.location.CityName;
import io.geovaneshimizu.whatstune.location.GeoCoordinates;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.param.Main;

@Service
class OpenWeatherService implements WeatherService {

    private final OWM openWeatherApi;

    private final Logger logger;

    OpenWeatherService(OWM openWeatherApi,
                       Logger logger) {
        this.openWeatherApi = openWeatherApi;
        this.logger = logger;
    }

    @Override
    public Optional<Weather> currentWeatherByCityName(CityName city) {
        try {
            CurrentWeather currentWeather = openWeatherApi.currentWeatherByCityName(city.asString());

            return Optional.of(currentWeather)
                    .filter(CurrentWeather::hasMainData)
                    .map(CurrentWeather::getMainData)
                    .filter(Main::hasTemp)
                    .map(Main::getTemp)
                    .map(temp -> Temperature.of(temp, Scale.CELSIUS))
                    .map(Weather::now);
        } catch (APIException e) {
            logger.warn("Could not get current weather data: {}", e.getMessage(), e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Weather> currentWeatherByCoordinates(GeoCoordinates coordinates) {
        try {
            CurrentWeather currentWeather =
                    openWeatherApi.currentWeatherByCoords(
                            coordinates.latitude().asDouble(),
                            coordinates.longitude().asDouble());

            return Optional.of(currentWeather)
                    .filter(CurrentWeather::hasMainData)
                    .map(CurrentWeather::getMainData)
                    .filter(Main::hasTemp)
                    .map(Main::getTemp)
                    .map(temp -> Temperature.of(temp, Scale.CELSIUS))
                    .map(Weather::now);
        } catch (APIException e) {
            logger.warn("Could not get current weather data: {}", e.getMessage(), e);
            return Optional.empty();
        }
    }
}
