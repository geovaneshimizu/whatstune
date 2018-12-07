package io.geovaneshimizu.whatstune.music;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import io.geovaneshimizu.whatstune.location.CityName;
import io.geovaneshimizu.whatstune.location.GeoCoordinates;
import io.geovaneshimizu.whatstune.weather.Climate;
import io.geovaneshimizu.whatstune.weather.WeatherService;

@Component
class ClimateCategorySelector implements CategorySelector {

    private final WeatherService weatherService;

    private final ClimateCategoryMapper climateCategoryMapper;

    private final Logger logger;

    ClimateCategorySelector(WeatherService weatherService,
                            ClimateCategoryMapper climateCategoryMapper,
                            Logger logger) {
        this.weatherService = weatherService;
        this.climateCategoryMapper = climateCategoryMapper;
        this.logger = logger;
    }

    @Override
    public Category byCityName(CityName cityName) {
        logger.debug("Selecting category by weather in {}", cityName);
        return weatherService.currentWeatherByCityName(cityName)
                .map(Climate::basedOn)
                .map(climateCategoryMapper)
                .orElse(Category.ROCK); // \m/
    }

    @Override
    public Category byGeoCoordinates(GeoCoordinates coordinates) {
        logger.debug("Selecting category by weather in {}", coordinates);
        return weatherService.currentWeatherByCoordinates(coordinates)
                .map(Climate::basedOn)
                .map(climateCategoryMapper)
                .orElse(Category.ROCK); // \m/
    }
}
