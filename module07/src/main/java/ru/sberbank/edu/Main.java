package ru.sberbank.edu;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {

    private JSONObject json;
    private double temp;
    private double pressure;
    private double feelsLike;
    public static final double CELSIUS = 273.15;

    public Main(String str) {
        JSONParser parser = new JSONParser();
        try {
            json = (JSONObject)parser.parse(str);
            this.json = (JSONObject) json.get("main");
            this.temp = getCelsius(json.get("temp").toString());
            this.pressure = Double.parseDouble(json.get("pressure").toString());
            this.feelsLike = getCelsius(json.get("feels_like").toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private double getCelsius(String str) {
        double d = Double.parseDouble(str);
        return Math.round((d - CELSIUS) * 1000) / 1000;
    }

    public double getTemp() {
        return temp;
    }

    public double getPressure() {
        return pressure;
    }

    public double getFeelsLike() {
        return feelsLike;
    }
}