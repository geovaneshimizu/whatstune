package io.geovaneshimizu.whatstune.music;

import java.util.function.Function;

import io.geovaneshimizu.whatstune.weather.Climate;

interface ClimateCategoryMapper extends Function<Climate, Category> {
}
