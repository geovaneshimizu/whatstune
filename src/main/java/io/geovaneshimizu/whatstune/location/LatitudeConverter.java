package io.geovaneshimizu.whatstune.location;

import org.springframework.core.convert.converter.Converter;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class LatitudeConverter implements Converter<String, Latitude> {

    @Override
    public Latitude convert(String value) {
        return Latitude.of(value);
    }
}
