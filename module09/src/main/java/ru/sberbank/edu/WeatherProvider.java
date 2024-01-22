package ru.sberbank.edu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class WeatherProvider implements InfoWeatherProvider {
    private RestTemplate restTemplate;
    private String url = "http://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}";

    @Value("${appKey}")
    private String appKey;

    @Autowired
    public WeatherProvider(@Qualifier("restTemplate") RestTemplate restTemplate, @Value("${appKey}") String appKey) {
        this.restTemplate = restTemplate;
        this.appKey = appKey;
    }

    /**
     * Download ACTUAL weather info from internet.
     * You should call GET http://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
     * You should use Spring Rest Template for calling requests
     *
     * @param city - city
     * @return weather info or null
     */
    public WeatherInfo get(String city) {
        WeatherInfo info = null;
        String res ="";
        try {
            ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class, city, appKey);
            res = entity.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("404 Not Found");
        }
        if (!res.isEmpty()) {
            Weather weather = new Weather(res);
            Wind wind = new Wind(res);
            Main main = new Main(res);
            info = new WeatherInfo(city, weather.getMain(), weather.getDescription(), main.getTemp(),
                    main.getFeelsLike(), wind.getSpeed(), main.getPressure());
        }
        return info;
    }
}
