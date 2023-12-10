package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        List<String> list = Arrays.asList("Moscow", "London", "Paris", "Seoul", "Tokyo", "K");
        //List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Thread thread = new MyThread(list.get(i));
            thread.start();
        }
    }


    public static class MyThread extends Thread {
        private String city;

        public MyThread(String city) {
            this.city = city;
        }

        @Override
        public void run() {
            WeatherCache cache = new WeatherCache(new WeatherProvider());
            WeatherInfo info = cache.getWeatherInfo(city);
            System.out.println("Weather=" + info);
        }
    }
}

