package io.geovaneshimizu.whatstune.weather;

enum Scale {

    CELSIUS(-30.0d, 50.0d);

    private final double minimum;

    private final double maximum;

    Scale(double minimum,
          double maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    double minimum() {
        return minimum;
    }

    double maximum() {
        return maximum;
    }
}
