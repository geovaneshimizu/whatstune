package io.geovaneshimizu.whatstune.music;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import io.geovaneshimizu.whatstune.location.CityName;
import io.geovaneshimizu.whatstune.location.GeoCoordinates;

@Service
class PlaylistService {

    private final PlaylistRepository playlistRepository;

    private final CategorySelector categorySelector;

    private final Logger logger;

    PlaylistService(PlaylistRepository playlistRepository,
                    CategorySelector categorySelector,
                    Logger logger) {
        this.playlistRepository = playlistRepository;
        this.categorySelector = categorySelector;
        this.logger = logger;
    }

    Playlist findByCityName(CityName cityName) {
        Category category = categorySelector.byCityName(cityName);
        logger.debug("Finding playlist for {}", category);
        return playlistRepository.findByCategory(category);
    }

    Playlist findByGeoCoordinates(GeoCoordinates coordinates) {
        Category category = categorySelector.byGeoCoordinates(coordinates);
        logger.debug("Finding playlist for {}", category);
        return playlistRepository.findByCategory(category);
    }
}
