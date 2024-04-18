package pl.example;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

public class DifferentApiTest {

    public static String getCatFact() {
        String apiUrl = "https://catfact.ninja/fact";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(apiUrl).openStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            JSONObject jsonObject = new JSONObject(response.toString());
            String fact = jsonObject.getString("fact");
            int length = jsonObject.getInt("length");

            System.out.println("Fact: " + fact);
            System.out.println("Length: " + length);
            return fact;

        } catch (IOException e) {
            e.printStackTrace();
            return "An error occurred while fetching cat fact.";
        }
    }

    public static Double getCurrentWeatherFromOpenWeather(){
        String city = "Warszawa";
        String apiKey =Config.getApiKey();
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(apiUrl).openStream()))) {
            StringBuilder loadedJsonFile = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                loadedJsonFile.append(line);
            }

            JSONObject jsonObject = new JSONObject(loadedJsonFile.toString());

            Integer firstIndexOfArray = jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id");
            String secondIndexOfArray = jsonObject.getJSONArray("weather").getJSONObject(0).getString("main");

            Double temp = jsonObject.getJSONObject("main").getDouble("temp");
            Integer clouds = jsonObject.getJSONObject("clouds").getInt("all");


            System.out.println("Weather array 1 index: " + firstIndexOfArray);
            System.out.println("Weather array 2 index: " + secondIndexOfArray);

            System.out.println("Temperatura w Krakowie wynosi: " + Math.round((temp-272.15)*100.0)/100.0);
            System.out.println("Clouds: " + clouds);


            return temp;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Double getForecastWeatherFromOpenWeather(){
        String city = "Warszawa";
        String apiKey =Config.getApiKey();
        String apiUrl = "https://api.openweathermap.org/data/2.5/forecast?q=" + city + "&appid=" + apiKey;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(apiUrl).openStream()))) {
            StringBuilder loadedJsonFile = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                loadedJsonFile.append(line);
            }
            JSONObject jsonObject = new JSONObject(loadedJsonFile.toString());
            JSONArray list = jsonObject.getJSONArray("list");

            List<Integer> loadedDateData= new ArrayList<>();
            List<Double> loadedTemperatureData = new ArrayList<>();
            List<Integer> loadedPressureData = new ArrayList<>();
            List<String> loadedIconData = new ArrayList<>();
            List<Integer> loadedCloudinessData = new ArrayList<>();
            List<Double> loadedWindSpeedData  = new ArrayList<>();
            List<Double> loadedPrecipitationProbabilityDate = new ArrayList<>();

            for(int i = 0; i < list.length(); i++){
                JSONObject packageIterator = list.getJSONObject(i);

                loadedDateData.add(packageIterator.getInt("dt"));
                loadedTemperatureData.add(packageIterator.getJSONObject("main").getDouble("temp"));
                loadedPressureData.add(packageIterator.getJSONObject("main").getInt("pressure"));
                loadedIconData.add(packageIterator.getJSONArray("weather").getJSONObject(0).getString("icon"));
                loadedCloudinessData.add(packageIterator.getJSONObject("clouds").getInt("all"));
                loadedWindSpeedData.add(packageIterator.getJSONObject("wind").getDouble("speed"));
                loadedPrecipitationProbabilityDate.add(packageIterator.getDouble("pop"));
            }

            System.out.println(loadedDateData);
            System.out.println(loadedTemperatureData);
            System.out.println(loadedPressureData);
            System.out.println(loadedIconData);
            System.out.println(loadedCloudinessData);
            System.out.println(loadedWindSpeedData);
            System.out.println(loadedPrecipitationProbabilityDate);


            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

