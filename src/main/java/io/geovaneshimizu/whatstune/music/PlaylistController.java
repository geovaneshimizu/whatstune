package io.geovaneshimizu.whatstune.music;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.geovaneshimizu.whatstune.location.CityName;
import io.geovaneshimizu.whatstune.location.GeoCoordinates;
import io.geovaneshimizu.whatstune.location.Latitude;
import io.geovaneshimizu.whatstune.location.Longitude;

@RestController
@RequestMapping("/api/playlist")
class PlaylistController {

    private final PlaylistService playlistService;

    private final Logger logger;

    PlaylistController(PlaylistService playlistService,
                       Logger logger) {
        this.playlistService = playlistService;
        this.logger = logger;
    }

    @GetMapping(params = "city")
    ResponseEntity<Playlist> getByCityName(@RequestParam("city") CityName cityName) {
        Playlist playlist = playlistService.findByCityName(cityName);
        logger.debug("Found {} for {}", playlist, cityName);
        return ResponseEntity.ok(playlist);
    }

    @GetMapping(params = {"lat", "lng"})
    ResponseEntity<Playlist> getByCoordinates(@RequestParam("lat") Latitude latitude,
                                              @RequestParam("lng") Longitude longitude) {
        GeoCoordinates geoCoordinates =
                GeoCoordinates
                        .withLatitude(latitude)
                        .andLongitude(longitude);

        Playlist playlist = playlistService.findByGeoCoordinates(geoCoordinates);
        logger.debug("Found {} for {}", playlist, geoCoordinates);
        return ResponseEntity.ok(playlist);
    }
}
