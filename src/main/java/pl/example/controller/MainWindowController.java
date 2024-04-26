package pl.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pl.example.model.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {

    public MainWindowController(String fxmlName, String title, String backgroundColor) {
        super(fxmlName, title, backgroundColor);
    }

    private WeatherService weatherService;

    @FXML
    private VBox mainVBox;
    @FXML
    private Label titleLabel;
    @FXML
    private Button checkWeather;
    @FXML
    private TextField cityName;

    @FXML
    private Label errorLabel;

    @FXML
    private Label localisationValue;
    @FXML
    private Label dayDateValue;

    @FXML
    private Label temperatureValue;
    @FXML
    private ImageView imageView;
    @FXML
    private Label cloudinessValue;
    @FXML
    private Label precipitationValue;
    @FXML
    private Label humidityValue;
    @FXML
    private Label pressureValue;
    @FXML
    private Label windSpeedValue;

    @FXML
    private BorderPane detailedWeatherDisplay;
    @FXML
    private HBox hourlyWeatherDisplay;
    @FXML
    private HBox dailyWeatherDisplay;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        weatherService = WeatherServiceFactory.createWeatherService();

        titleLabel.setText(title);
        mainVBox.setStyle("-fx-background-color: " + backgroundColor + ";");
        detailedWeatherDisplay.setVisible(false);
        hourlyWeatherDisplay.setVisible(false);
        dailyWeatherDisplay.setVisible(false);

    }

    @FXML
    public void checkWeatherAction() throws IOException, URISyntaxException, InterruptedException {

        if (cityName.getText().isEmpty()) {
            errorLabel.setText("Nie wprowadzono miasta!");
            return;
        }

        try{
            WeatherForecast weatherForecast = weatherService.getWeatherForecast(cityName.getText());
            displayWeather(weatherForecast);

        }catch (IOException | URISyntaxException| InterruptedException e){
            errorLabel.setText("Wystąpił błąd. " + e.getMessage());
        }
    }

    private void displayWeather(WeatherForecast weatherForecast){
        cleanErrorLabel();
        displayWindowTemplate();

        Collection<SingleDayWeather> weathers =  weatherForecast.getWeathers();
        SingleDayWeather startingDayWeather = weathers.iterator().next();
        Collection<DataPackage> startingThreeHourWeatherList = startingDayWeather.getThreeHourWeatherList();
        DataPackage startingPackage = startingThreeHourWeatherList.iterator().next();

        setTitleLabel(weatherForecast);
        setDetailedWeather(startingPackage);
        setHourlyWeather(startingThreeHourWeatherList);
        setDailyWeather(weathers);
    }

    private void setTitleLabel(WeatherForecast weatherForecast) {
        localisationValue.setText(weatherForecast.getCountry() + ", " + weatherForecast.getCity());
    }

    private void setDetailedWeather(DataPackage dataPackage) {
        dayDateValue.setText(dataPackage.getDate().format(DateTimeFormatter.ofPattern("EEEE, d MMMM")));
        temperatureValue.setText(String.valueOf(Math.round(dataPackage.getTemperature())) + "°C");
        imageView.setImage(new Image(String.valueOf(getClass().getResource("/pl/example/view/icons/" + dataPackage.getIcon() + ".png"))));
        cloudinessValue.setText(String.valueOf(dataPackage.getCloudiness()) + "%");
        precipitationValue.setText(String.valueOf(Math.round(dataPackage.getPrecipitationProbability()* 100)) + "%");
        humidityValue.setText(String.valueOf(dataPackage.getHumidity()) + "%");
        pressureValue.setText(String.valueOf(dataPackage.getPressure()) + "hPa");
        windSpeedValue.setText(String.valueOf(dataPackage.getWindSpeed()) + "m/s");
    }

    private void setHourlyWeather(Collection<DataPackage> threeHourWeatherList) {
        clearHourlyWeather();
        for(DataPackage dataPackage : threeHourWeatherList){
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.TOP_CENTER);

            Label timeLabel = new Label(dataPackage.getTime().toString());
            ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/pl/example/view/icons/" + dataPackage.getIcon() + ".png")).toExternalForm()));
            imageView.setFitHeight(48.0);
            imageView.setFitWidth(47.0);
            Label tempLabel = new Label(String.valueOf(Math.round(dataPackage.getTemperature())) + "°C");
            vBox.getChildren().addAll(timeLabel, imageView, tempLabel);
            DropShadow dropShadow = new DropShadow();
            vBox.setOnMouseEntered(event -> vBox.setEffect(dropShadow));
            vBox.setOnMouseExited(event -> vBox.setEffect(null));

            vBox.setOnMouseClicked(event -> handleHourlyWeatherVBoxClick(dataPackage));
            hourlyWeatherDisplay.getChildren().add(vBox);
        }
    }

    private void setDailyWeather(Collection<SingleDayWeather> weathers) {
        clearDailyWeather();

        for(SingleDayWeather singleDayWeather : weathers){
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.TOP_CENTER);
            vBox.setPrefWidth(100);
            vBox.setPrefHeight(200);
            vBox.setStyle("-fx-border-color: grey; -fx-border-radius: 5;");

            Label dayLabel = new Label();
            dayLabel.setText(singleDayWeather.getDate().format(DateTimeFormatter.ofPattern("E")));
            ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/pl/example/view/icons/" + singleDayWeather.getIcon() + ".png")).toExternalForm()));
            imageView.setFitHeight(41);
            imageView.setFitWidth(57);
            imageView.setPreserveRatio(true);
            Label tempLabel = new Label(String.valueOf(singleDayWeather.getMaxTemp()) + "/" + String.valueOf(singleDayWeather.getMinTemp()) + "°C");
            vBox.getChildren().addAll(dayLabel, imageView, tempLabel);

            HBox.setMargin(vBox, new Insets(0, 5, 0, 5));
            DropShadow dropShadow = new DropShadow();
            vBox.setOnMouseEntered(event -> vBox.setEffect(dropShadow));
            vBox.setOnMouseExited(event -> vBox.setEffect(null));

            vBox.setOnMouseClicked(event -> handleDailyWeatherVBoxClick(singleDayWeather.getThreeHourWeatherList()));
            dailyWeatherDisplay.getChildren().add(vBox);
        }
    }

    private void cleanErrorLabel(){
        errorLabel.setText("");
    }

    private void clearHourlyWeather(){
        hourlyWeatherDisplay.getChildren().clear();
    }

    private void clearDailyWeather(){
        dailyWeatherDisplay.getChildren().clear();
    }

    private void handleHourlyWeatherVBoxClick(DataPackage dataPackage) {
        setDetailedWeather(dataPackage);
    }

    private void handleDailyWeatherVBoxClick(Collection<DataPackage> threeHourWeatherList) {
        setHourlyWeather(threeHourWeatherList);
        setDetailedWeather(threeHourWeatherList.iterator().next());
    }

    private void displayWindowTemplate() {
        detailedWeatherDisplay.setVisible(true);
        hourlyWeatherDisplay.setVisible(true);
        dailyWeatherDisplay.setVisible(true);
    }
}
