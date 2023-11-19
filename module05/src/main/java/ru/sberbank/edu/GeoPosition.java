package ru.sberbank.edu;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * Geo position.
 */
public class GeoPosition {

    /**
     * Широта в радианах.
     */
    private double latitude;

    /**
     * Долгота в радианах.
     */
    private double longitude;

    /**
     * Ctor.
     *
     * @param latitudeGradus  - latitude in gradus
     * @param longitudeGradus - longitude in gradus
     *                        Possible values: 55, 55(45'07''), 59(57'00'')
     */
    public GeoPosition(String latitudeGradus, String longitudeGradus) {
        this.latitude = ofStringToDouble(latitudeGradus);
        this.longitude = ofStringToDouble(longitudeGradus);
    }

    private double ofStringToDouble (String gradus) {
        StringJoiner joinerGradus = new StringJoiner(".");
        String[] numbers = gradus.split("[^0-9]");
        Arrays.stream(numbers)
                .toList()
                .stream()
                .forEach(s -> joinerGradus.add(s));
        return Double.parseDouble(String.valueOf(joinerGradus));
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "GeoPosition{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}