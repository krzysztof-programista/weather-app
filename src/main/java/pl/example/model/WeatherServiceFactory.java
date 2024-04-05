package pl.example.model;

import pl.example.model.client.ExampleWeatherClient;
import pl.example.model.client.MyWeatherClient;
import pl.example.model.client.WeatherClient;

public class WeatherServiceFactory {

    public static WeatherService createWeatherService() {
        return new WeatherService(createWeatherClient());
    }

    private static WeatherClient createWeatherClient() {
        return new ExampleWeatherClient();
        //return new MyWeatherClient();
    }
}
