package io.geovaneshimizu.whatstune.music;

import java.util.EnumMap;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import io.geovaneshimizu.whatstune.weather.Climate;

@Component
class InMemoryClimateCategoryMapper implements ClimateCategoryMapper {

    private static final EnumMap<Climate, Category> climateToCategory = new EnumMap<>(Climate.class);

    static {
        climateToCategory.put(Climate.HOT, Category.PARTY);
        climateToCategory.put(Climate.COOL, Category.POP);
        climateToCategory.put(Climate.CHILLY, Category.ROCK);
        climateToCategory.put(Climate.FREEZING, Category.CLASSICAL);
    }

    private final Logger logger;

    InMemoryClimateCategoryMapper(Logger logger) {
        this.logger = logger;
    }

    @Override
    public Category apply(Climate climate) {
        logger.debug("Getting category for {}", climate);
        return climateToCategory.getOrDefault(climate, Category.ROCK); // \m/
    }
}
