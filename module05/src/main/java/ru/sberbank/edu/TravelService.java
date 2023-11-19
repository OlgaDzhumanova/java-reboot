package ru.sberbank.edu;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Travel Service.
 */
public class TravelService {

    // do not change type
    private final List<CityInfo> cities = new ArrayList<>();

    /**
     * Append city info.
     *
     * @param cityInfo - city info
     * @throws IllegalArgumentException if city already exists
     */
    public void add(CityInfo cityInfo) throws IllegalArgumentException {
            if (!cities.contains(cityInfo) && cityInfo != null) {
                cities.add(cityInfo);
            }
    }

    /**
     * remove city info.
     *
     * @param cityName - city name
     * @throws IllegalArgumentException if city doesn't exist
     */
    public void remove(String cityName) throws IllegalArgumentException {
        String name = "";
        for (int i = 0; i < cities.size(); i++) {
            name = cities.get(i).getName();
            if (name.equals(cityName)) {
                cities.remove(cities.get(i));
            }
        }
    }

    /**
     * Get cities names.
     */
    public List<String> citiesNames() {
        List<String> citiesNames = cities.stream()
                                  .map(s -> s.getName())
                                  .collect(Collectors.toList());
        return citiesNames;
    }

    /**
     * Get distance in kilometers between two cities.
     * https://www.kobzarev.com/programming/calculation-of-distances-between-cities-on-their-coordinates/
     *
     * @param srcCityName  - source city
     * @param destCityName - destination city
     * @throws IllegalArgumentException if source or destination city doesn't exist.
     */
    public int getDistance(String srcCityName, String destCityName) throws IllegalArgumentException {
            double earthRadius = 6371.01;
            GeoPosition srcCity = getPosition(srcCityName).get();
            GeoPosition destCity = getPosition(destCityName).get();
            double latSrc = Math.toRadians(srcCity.getLatitude());
            double lonSrc = Math.toRadians(srcCity.getLongitude());
            double latDest = Math.toRadians(destCity.getLatitude());
            double lonDest = Math.toRadians(destCity.getLongitude());
            return (int) (earthRadius * Math.acos(Math.sin(latSrc) * Math.sin(latDest)
                    + Math.cos(latSrc) * Math.cos(latDest) * Math.cos(lonSrc - lonDest)));
    }

    /**
     * Get all cities near current city in radius.
     *
     * @param cityName - city
     * @param radius   - radius in kilometers for search
     * @throws IllegalArgumentException if city with cityName city doesn't exist.
     */
    public List<String> getCitiesNear(String cityName, int radius) throws IllegalArgumentException{
        List<String> citiesNear =  cities.stream()
                .map(s -> s.getName())
                .filter(s -> (getDistance(cityName, s) <= radius) && !s.equals(cityName))
                .collect(Collectors.toList());
        return citiesNear;
    }

    private Optional<GeoPosition> getPosition (String city) {
        GeoPosition geoPosition = null;
        for (CityInfo c: cities) {
            if (c.getName().equals(city)) {
                geoPosition = c.getPosition();
            }
        }
        return Optional.ofNullable(geoPosition);
    }
}
