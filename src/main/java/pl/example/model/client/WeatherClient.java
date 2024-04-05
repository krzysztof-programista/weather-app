package pl.example.model.client;

import pl.example.model.SingleDayWeather;
import pl.example.model.WeatherForecast;

import java.util.Collection;
import java.util.List;

public interface WeatherClient {

    SingleDayWeather currentWeather(String cityName);

    Collection<SingleDayWeather> forecast(String cityName);

    WeatherForecast getWeather(String cityName, List<SingleDayWeather> result);
}
