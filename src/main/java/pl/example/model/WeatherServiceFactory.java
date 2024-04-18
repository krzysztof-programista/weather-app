package pl.example.model;

import pl.example.model.client.OpenWeatherMapClient;
import pl.example.model.client.WeatherClient;

public class WeatherServiceFactory {

    public static WeatherService createWeatherService() {
        return new WeatherService(createWeatherClient());
    }

    private static WeatherClient createWeatherClient() {
        return new OpenWeatherMapClient();
    }
}
