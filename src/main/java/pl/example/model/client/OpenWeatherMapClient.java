package pl.example.model.client;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pl.example.Config;
import pl.example.model.CountryDecoder;
import pl.example.model.DataPackage;
import pl.example.model.SingleDayWeather;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class OpenWeatherMapClient implements WeatherClient{

    private final HttpClient client;
    private String country = "";
    private String city = "";

    public OpenWeatherMapClient() {
        this.client = HttpClient.newBuilder()
                .build();
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public Collection<SingleDayWeather> getFiveDaysForecast(String cityName) throws IOException, InterruptedException {

        HttpResponse<String> response = getHttpResponse(cityName);
        int responseCode = response.statusCode();

        if(responseCode != 200){
            handleHttpError(responseCode, cityName);
            return Collections.emptyList();
        }else {
            return retrieveData(response);
        }
    }

    private HttpResponse<String> getHttpResponse(String cityName) throws IOException, InterruptedException {
        String apiKey = Config.getApiKey();
        String encodedCityName = URLEncoder.encode(cityName, StandardCharsets.UTF_8);

        HttpRequest request = HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .uri(URI.create("https://api.openweathermap.org/data/2.5/forecast?q=" + encodedCityName + "&appid=" + apiKey))
                .build();

        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        }catch (ConnectException e) {
            throw new IOException("Connection failed. Please check your internet connection and try again.", e);
        }
    }

    private void handleHttpError(int responseCode, String cityName) throws IOException {

        switch (responseCode) {
            case 401:
                throw new IOException("Nieautoryzowany dostęp podczas pobierania danych pogodowych. Sprawdź swój klucz API.");
            case 404:
                throw new IOException("Nie znaleziono miasta: " + cityName);
            case 500:
                throw new IOException("Wewnętrzny błąd serwera po stronie dostawcy danych pogodowych.");
            default:
                throw new IOException("Nie udało się pobrać danych: kod stanu HTTP " + responseCode);
        }
    }

    private Collection<SingleDayWeather> retrieveData(HttpResponse<String> response) throws IOException {
        LocalDate today = LocalDate.now();
        Map<LocalDate, List<DataPackage>> dailyWeatherData  = new HashMap<>();

        parseWeatherData(response, today, dailyWeatherData);

        return buildFiveDayForecast(today, dailyWeatherData);
    }

    private void parseWeatherData(HttpResponse<String> response, LocalDate today, Map<LocalDate, List<DataPackage>> dailyWeatherData) throws IOException {

        try {
            JSONObject dataFromApi = new JSONObject(response.body());
            JSONArray weatherDataFromApi = dataFromApi.getJSONArray("list");
            this.city = dataFromApi.getJSONObject("city").getString("name");
            this.country = CountryDecoder.getFullNameOfCountry(dataFromApi.getJSONObject("city").getString("country"));


            for (int i = 0; i < weatherDataFromApi.length(); i++) {
                JSONObject threeHourWeather = weatherDataFromApi.getJSONObject(i);
                LocalDate date = LocalDate.parse(threeHourWeather.getString("dt_txt").substring(0, 10));

                if (!date.isBefore(today) && !date.isAfter(today.plusDays(4))) {
                    dailyWeatherData.computeIfAbsent(date, k -> new ArrayList<>())
                            .add(extractDataPackage(threeHourWeather, date));
                }
            }
        }catch (JSONException e) {
            throw new IOException("Error parsing weather data response", e);
        }
    }

    private DataPackage extractDataPackage(JSONObject threeHourWeather, LocalDate date) {
        double temperature = Math.round(convertKelvinToCelsius(threeHourWeather.getJSONObject("main").getDouble("temp")));
        int pressure = threeHourWeather.getJSONObject("main").getInt("pressure");
        int humidity = threeHourWeather.getJSONObject("main").getInt("humidity");
        String icon = threeHourWeather.getJSONArray("weather").getJSONObject(0).getString("icon");
        int cloudiness = threeHourWeather.getJSONObject("clouds").getInt("all");
        double windSpeed = threeHourWeather.getJSONObject("wind").getDouble("speed");
        double precipitationProbability = threeHourWeather.getDouble("pop");
        LocalTime time = LocalTime.parse(threeHourWeather.getString("dt_txt").substring(11, 19));

        return new DataPackage(temperature, pressure, humidity, icon, cloudiness, windSpeed, precipitationProbability, date, time);
    }

    private double convertKelvinToCelsius(double kelvinValue) {
        return kelvinValue - 272.15;
    }

    private Collection<SingleDayWeather> buildFiveDayForecast(LocalDate today, Map<LocalDate, List<DataPackage>> dailyWeatherData) {
        Collection<SingleDayWeather> fiveDayForecast  = new ArrayList<>();

        today.datesUntil(today.plusDays(5)).forEach(day ->
                fiveDayForecast.add(new SingleDayWeather(dailyWeatherData.getOrDefault(day, Collections.emptyList())))
        );

        return fiveDayForecast ;
    }

}


