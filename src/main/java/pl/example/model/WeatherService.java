package pl.example.model;

import pl.example.model.client.WeatherClient;

public class WeatherService {

    private final WeatherClient weatherClient;

    public WeatherService(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    public Weather getWeather(String cityname) {
        return weatherClient.getWeather(cityname);
    }
}
