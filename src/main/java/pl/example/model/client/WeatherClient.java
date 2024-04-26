package pl.example.model.client;

import pl.example.model.SingleDayWeather;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

public interface WeatherClient {

    String getCity();
    String getCountry();
    Collection<SingleDayWeather> getFiveDaysForecast(String cityName) throws IOException, URISyntaxException, InterruptedException;

}
