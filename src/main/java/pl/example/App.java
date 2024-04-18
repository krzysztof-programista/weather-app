package pl.example;

import javafx.application.Application;

import javafx.stage.Stage;
import pl.example.model.client.OpenWeatherMapClient;

public class App extends Application
{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

//        ViewFactory viewFactory = new ViewFactory();
//        viewFactory.showMainWindow(primaryStage);

        OpenWeatherMapClient openWeatherMapClient = new OpenWeatherMapClient();

        System.out.println(openWeatherMapClient.getFiveDaysForecast("Poznań"));
    }


}
