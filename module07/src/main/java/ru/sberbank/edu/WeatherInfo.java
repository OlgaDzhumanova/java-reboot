package ru.sberbank.edu;

import java.time.LocalDateTime;
import java.util.Objects;

public class WeatherInfo {

    private String city;

    /**
     * Short weather description
     * Like 'sunny', 'clouds', 'raining', etc
     */
    private String shortDescription;

    /**
     * Weather description.
     * Like 'broken clouds', 'heavy raining', etc
     */
    private String description;

    /**
     * Temperature.
     */
    private double temperature;

    /**
     * Temperature that fells like.
     */
    private double feelsLikeTemperature;

    /**
     * Wind speed.
     */
    private double windSpeed;

    /**
     * Pressure.
     */
    private double pressure;

    /**
     * Expiry time of weather info.
     * If current time is above expiry time then current weather info is not actual!
     */
    private LocalDateTime expiryTime;

    public WeatherInfo(String city, String shortDescription, String description,
                       double temperature, double feelsLikeTemperature, double windSpeed, double pressure) {
        this.city = city;
        this.shortDescription = shortDescription;
        this.description = description;
        this.temperature = temperature;
        this.feelsLikeTemperature = feelsLikeTemperature;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
        this.expiryTime = LocalDateTime.now();
    }

    public String getCity() {
        return city;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getFeelsLikeTemperature() {
        return feelsLikeTemperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getPressure() {
        return pressure;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherInfo that = (WeatherInfo) o;
        return Double.compare(temperature, that.temperature) == 0
                && Double.compare(feelsLikeTemperature, that.feelsLikeTemperature) == 0
                && Double.compare(windSpeed, that.windSpeed) == 0
                && Double.compare(pressure, that.pressure) == 0
                && Objects.equals(city, that.city)
                && Objects.equals(shortDescription, that.shortDescription)
                && Objects.equals(description, that.description)
                && Objects.equals(expiryTime, that.expiryTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, shortDescription, description, temperature,
                feelsLikeTemperature, windSpeed, pressure, expiryTime);
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "city='" + city + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", description='" + description + '\'' +
                ", temperature=" + temperature +
                ", feelsLikeTemperature=" + feelsLikeTemperature +
                ", windSpeed=" + windSpeed +
                ", pressure=" + pressure +
                ", expiryTime=" + expiryTime +
                '}';
    }
}
