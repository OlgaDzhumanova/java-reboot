package ru.sberbank.edu;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Weather {
    private JSONObject json;
    private String description;
    private String main;

    public Weather(String str) {
        JSONParser parser = new JSONParser();
        try {
            json = (JSONObject)parser.parse(str);
            JSONArray array = (JSONArray) json.get("weather");
            this.json = (JSONObject) array.get(0);
            this.description = json.get("description").toString();
            this.main = json.get("main").toString();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDescription() {
        return description;
    }

    public String getMain() {
        return main;
    }
}