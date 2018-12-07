package io.geovaneshimizu.whatstune.location;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CityNameTests {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void createWhenNameIsNullShouldThrowException() {
        this.thrown.expect(NullPointerException.class);
        this.thrown.expectMessage("City name must not be null");
        CityName.of(null);
    }

    @Test
    public void createWhenNameIsEmptyShouldThrowException() {
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("Invalid city name: empty value");
        CityName.of("");
    }

    @Test
    public void asStringShouldReturnName() {
        CityName cityName = CityName.of("Campinas");
        assertThat(cityName.asString()).isEqualTo("Campinas");
    }

    @Test
    public void equalsAndHashCodeShouldBeBasedOnName() {
        CityName cityName_01 = CityName.of("Campinas");
        CityName cityName_02 = CityName.of("Campinas");
        CityName cityName_03 = CityName.of("São Paulo");

        assertThat(cityName_02.hashCode()).isEqualTo(cityName_01.hashCode());
        assertThat(cityName_02).isEqualTo(cityName_01);
        assertThat(cityName_03).isNotEqualTo(cityName_01);
    }
}
