package ru.sberbank.edu;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Wind {
    private JSONObject json;
    private double speed;


    public Wind(String str) {
        JSONParser parser = new JSONParser();
        try {
            json = (JSONObject)parser.parse(str);
            this.json = (JSONObject) json.get("wind");
            this.speed = Double.parseDouble(json.get("speed").toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public double getSpeed() {
        return speed;
    }
}