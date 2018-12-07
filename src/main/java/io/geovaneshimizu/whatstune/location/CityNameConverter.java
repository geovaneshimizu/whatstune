package io.geovaneshimizu.whatstune.location;

import org.springframework.core.convert.converter.Converter;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class CityNameConverter implements Converter<String, CityName> {

    @Override
    public CityName convert(String value) {
        return CityName.of(value);
    }
}
