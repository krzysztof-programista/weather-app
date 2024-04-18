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

    public WeatherForecast getWeatherForecast(String cityName) throws IOException, URISyntaxException, InterruptedException {
        Collection<SingleDayWeather> forecast = weatherClient.getFiveDaysForecast(cityName);

        return new WeatherForecast(cityName, forecast); //przekazuje pgodody dniowe do prognozy pogody
    }
}


