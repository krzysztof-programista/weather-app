package pl.example.model.client;

import pl.example.model.Weather;

import java.time.LocalDate;

public class MyWeatherClient implements WeatherClient{

    @Override
    public Weather getWeather(String cityName) {
        return new Weather(cityName,15, LocalDate.now());
    }
}
