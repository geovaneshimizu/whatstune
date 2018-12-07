# WhatsTune

Find out a music playlist according to the current temperature of a location, either by the city name, or by latitude 
and longitude coordinates.

## Business rules

* If temperature (Celsius) is above 30 degrees, the service suggests tracks for party
* In case temperature is between 15 and 30 degrees, it suggests pop music tracks
* If it's a bit chilly (between 10 and 14 degrees), it suggests rock music tracks
* Otherwise, if it's freezing outside, it suggests classical music tracks

## API Guide

### `Playlist` Resource

#### Get a `Playlist` by City Name

`GET /api/playlist?city={cityName}`

#### Get a `Playlist` by Coordinates

`GET /api/playlist?lat={latitude}&lng={longitude}`

## Developing

### System Requirements

- Java 1.8+

### Building

```
$ ./mvnw clean install
```

### Running

```
$ java -jar target/whatstune-0.0.1-SNAPSHOT.jar
```

### Docker container

```
$ ./docker-build.sh
```

```
docker run -p 8080:8080 \
-e WHATSTUNE_SPOTIFY_CLIENT_ID=${SPOTIFY_CLIENT_ID} \
-e WHATSTUNE_SPOTIFY_CLIENT_SECRET=${SPOTIFY_CLIENT_SECRET} \
-e WHATSTUNE_OPEN_WEATHER_API_KEY=${OPEN_WEATHER_MAP_API_KEY} \
-d io.geovaneshimizu/whatstune
```
