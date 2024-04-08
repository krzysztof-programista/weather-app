package pl.example.model;

import pl.example.model.client.WeatherClient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WeatherService {

    private final WeatherClient weatherClient;

    public WeatherService(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    public WeatherForecast getWeather(String cityName) {

        SingleDayWeather currentWeather = weatherClient.currentWeather(cityName);
        Collection<SingleDayWeather> forecast = weatherClient.forecast(cityName);

        List<SingleDayWeather> result = new ArrayList<>(forecast);

        result.add(currentWeather);

        return new WeatherForecast(cityName, result);
    }
}
