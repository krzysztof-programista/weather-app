package pl.example.model;

import pl.example.model.client.WeatherClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WeatherService {

    private final WeatherClient weatherClient;

    public WeatherService(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }


    public WeatherForecast getWeatherForecast(String requestedCityName) throws IOException, URISyntaxException, InterruptedException {

        Collection<SingleDayWeather> forecast = weatherClient.getFiveDaysForecast(requestedCityName);
        String city = weatherClient.getCity();
        String country = weatherClient.getCountry();

        return new WeatherForecast(city,country,forecast);
    }
}


