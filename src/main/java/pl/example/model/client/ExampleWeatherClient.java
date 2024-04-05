package pl.example.model.client;

import pl.example.model.Weather;

import java.time.LocalDate;

public class ExampleWeatherClient implements WeatherClient{

    @Override
    public Weather getWeather(String cityName) {
        return new Weather(cityName,10, LocalDate.now());
    }
}
