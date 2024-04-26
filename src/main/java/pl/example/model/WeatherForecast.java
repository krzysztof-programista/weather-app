package pl.example.model;

import java.util.Collection;

public class WeatherForecast {

    private final String city;
    private final String country;
    private final Collection<SingleDayWeather> weathers;

    public WeatherForecast(String city,String country, Collection<SingleDayWeather> weathers) {
        this.city = city;
        this.country = country;
        this.weathers = weathers;
    }

    public String getCity() {
        return city;
    }
    public String getCountry() {
        return country;
    }
    public Collection<SingleDayWeather> getWeathers() {
        return weathers;
    }

}
