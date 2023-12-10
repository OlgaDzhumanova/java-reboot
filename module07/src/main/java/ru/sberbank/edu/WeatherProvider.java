package ru.sberbank.edu;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class WeatherProvider {
    private RestTemplate restTemplate;
    private String url = "http://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}";
    public static final String API_KEY = "6cafe32e354d65856ab563ecd2126b1f";

    public WeatherProvider() {
        this.restTemplate = new RestTemplate();
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
            ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class, city, API_KEY);
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
