package io.geovaneshimizu.whatstune.music;

import io.geovaneshimizu.whatstune.location.CityName;
import io.geovaneshimizu.whatstune.location.GeoCoordinates;

interface CategorySelector {

    Category byCityName(CityName cityName);

    Category byGeoCoordinates(GeoCoordinates coordinates);
}
