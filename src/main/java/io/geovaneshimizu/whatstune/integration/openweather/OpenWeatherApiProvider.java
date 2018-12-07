package io.geovaneshimizu.whatstune.integration.openweather;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.aksingh.owmjapis.core.OWM;

@Configuration
class OpenWeatherApiProvider {

    @Bean
    OWM openWeatherApi(OpenWeatherProperties properties) {
        OWM owm = new OWM(properties.getApiKey());
        owm.setUnit(OWM.Unit.METRIC); // Could be a property
        return owm;
    }
}
