package pl.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import pl.example.DifferentApiTest;
import pl.example.model.WeatherForecast;
import pl.example.model.WeatherService;
import pl.example.model.WeatherServiceFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {

    @FXML
    private ToggleButton checkWeather;
    @FXML
    private Label temperature;
    @FXML
    private Label temperatureLabel;
    @FXML
    private Label temperature1;
    @FXML
    private Label temperature2;
    @FXML
    private Label temperature3;
    @FXML
    private Label temperature4;
    @FXML
    private Label temperature5;
    private WeatherService weatherService;

    public MainWindowController(String fxmlName) {
        super(fxmlName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        weatherService = WeatherServiceFactory.createWeatherService();
        temperature.setVisible(false);
        temperatureLabel.setVisible(false);
    }

    @FXML
    public void checkWeatherAction() throws IOException, URISyntaxException, InterruptedException {
        String cityName = "Kraków";

        WeatherForecast weatherForecast = weatherService.getWeatherForecast(cityName);

        //System.out.println(weatherForecast.forToday());
        System.out.println(weatherForecast.getWeathers());
        displayWeather(weatherForecast);


    }

    private void displayWeather(WeatherForecast weatherForecast){
        temperature.setVisible(true);
        temperatureLabel.setVisible(true);
        //temperature.setText("" + weatherForecast.forToday().getTempInCelsius());
    }
}
