package ru.sberbank.edu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class WeatherCache {

    private final Map<String, WeatherInfo> cache = new HashMap<>();
    private final InfoWeatherProvider infoWeatherProvider;

    /**
     * Constructor.
     *
     * @param infoWeatherProvider - weather provider
     */

    @Autowired
    public WeatherCache(@Qualifier("infoWeatherProvider") InfoWeatherProvider infoWeatherProvider) {

        this.infoWeatherProvider = infoWeatherProvider;
    }

    /**
     * Get ACTUAL weather info for current city or null if current city not found.
     * If cache doesn't contain weather info OR contains NOT ACTUAL info then we should download info
     * If you download weather info then you should set expiry time now() plus 5 minutes.
     * If you can't download weather info then remove weather info for current city from cache.
     *
     * @param city - city
     * @return actual weather info
     */
    public synchronized WeatherInfo getWeatherInfo(String city) {
        WeatherInfo info = infoWeatherProvider.get(city);
        LocalDateTime dateTime = LocalDateTime.now().plusMinutes(5);
        if (!cache.containsKey(city) || cache.get(city).getExpiryTime().isAfter(dateTime)) {
            cache.put(city, info);
        }
        return info;
    }

    /**
     * Remove weather info from cache.
     **/
    public void removeWeatherInfo(String city) {
        cache.remove(city);
    }
}