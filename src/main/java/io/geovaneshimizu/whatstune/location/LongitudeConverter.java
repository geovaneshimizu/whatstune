package io.geovaneshimizu.whatstune.location;

import org.springframework.core.convert.converter.Converter;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class LongitudeConverter implements Converter<String, Longitude> {

    @Override
    public Longitude convert(String value) {
        return Longitude.of(value);
    }
}
