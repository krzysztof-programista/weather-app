package pl.example.model.client;

import pl.example.model.WeatherForecast;

import java.time.LocalDate;

public class ExampleWeatherClient implements WeatherClient{

    @Override
    public WeatherForecast getWeather(String cityName) {
        return new WeatherForecast(cityName,x);
    }
}
