package ru.sberbank.edu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("app.properties")
@ComponentScan(basePackages = "ru.sberbank.edu")
public class MyConfig {

    @Value("${appKey}")
    private String appKey;

    @Bean("restTemplate")
    public RestTemplate restTemplate() {
    return new RestTemplate();
    }

    @Bean("infoWeatherProvider")
    public InfoWeatherProvider infoWeatherProvider() {
        return new WeatherProvider(restTemplate(), appKey);
    }

    @Bean()
    public WeatherCache weatherCache() {
        return new WeatherCache(infoWeatherProvider());
    }
}
