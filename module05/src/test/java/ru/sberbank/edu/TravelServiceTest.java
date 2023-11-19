package ru.sberbank.edu;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class TravelServiceTest {
    @Test
    public void whenReturnCitiesNamesList() {
        TravelService travelService = new TravelService();
        travelService.add(new CityInfo("Moscow", new GeoPosition("55,75", "37,61")));
        travelService.add(new CityInfo("Tula", new GeoPosition("54,12", "37,37")));
        travelService.add(new CityInfo("Nizhniy Novgorod", new GeoPosition("56,19", "44,00")));
        travelService.add(new CityInfo("Samara", new GeoPosition("53,12", "50,06")));

        List<String> expect = List.of("Moscow", "Tula", "Nizhniy Novgorod", "Samara");

        assertEquals(expect, travelService.citiesNames());
    }

    @Test
    public void whenGetCitiesNearIs181kmFromMoscow() {
        TravelService travelService = new TravelService();
        travelService.add(new CityInfo("Moscow", new GeoPosition("55'75", "37'61")));
        travelService.add(new CityInfo("Tula", new GeoPosition("54'12", "37'37")));
        travelService.add(new CityInfo("Nizhniy Novgorod", new GeoPosition("56,19", "44,00")));
        travelService.add(new CityInfo("Samara", new GeoPosition("53,12", "50,06")));

        List<String> expect = List.of("Tula");

        assertEquals(expect, travelService.getCitiesNear("Moscow", 181));
    }

    @Test
    public void whenRemoveMoscow() {
        TravelService travelService = new TravelService();
        travelService.add(new CityInfo("Moscow", new GeoPosition("55'75", "37'61")));
        travelService.add(new CityInfo("Tula", new GeoPosition("54'12", "37'37")));
        travelService.add(new CityInfo("Nizhniy Novgorod", new GeoPosition("56,19", "44,00")));
        travelService.add(new CityInfo("Samara", new GeoPosition("53,12", "50,06")));

        List<String> expect = List.of("Tula", "Nizhniy Novgorod", "Samara");

        travelService.remove("Moscow");

        assertEquals(expect, travelService.citiesNames());
    }
}