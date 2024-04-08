package pl.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import pl.example.model.WeatherForecast;
import pl.example.model.WeatherService;
import pl.example.model.WeatherServiceFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {

    @FXML
    private ToggleButton checkWeather;
    @FXML
    private Label temperature;
    @FXML
    private Label temperatureLabel;
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
    public void checkWeatherAction() {
        String cityName = "Kraków";

        WeatherForecast weather = weatherService.getWeather(cityName);

        displayWeather(weather);

    }

    private void displayWeather(WeatherForecast weather){
        temperature.setVisible(true);
        temperatureLabel.setVisible(true);
        temperature.setText("" + weather.getWeathers());
    }
}
