package pl.example.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pl.example.controller.BaseController;
import pl.example.controller.MainWindowController;

import java.io.IOException;

public class ViewFactory {

    private static final int MAIN_WINDOW_HEIGHT = 850;
    private static final int MAIN_WINDOW_WIDTH = 1375;

    private static final BorderPane MAIN_VIEW = (BorderPane) loadFXML(new MainWindowController("MainWindow"));
    private static final Scene SCENE = new Scene(MAIN_VIEW);

    private static Parent loadFXML(BaseController controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewFactory.class.getResource(controller.getFxmlName() + ".fxml"));
        fxmlLoader.setController(controller);

        try {
            return fxmlLoader.load();
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error with loadFXML function!");
            return null;
        }
    }

    public static void showMainWindow(Stage primaryStage){
        primaryStage.setScene(SCENE);
        primaryStage.setMinWidth(MAIN_WINDOW_WIDTH);
        primaryStage.setMinHeight(MAIN_WINDOW_HEIGHT);
        primaryStage.setMaximized(false);
        primaryStage.show();
    }

}























//    public void showMainWindow(){
//        BaseController controller = new MainWindowController(this, "MainWindow");
//        initializeStage(controller);
//    }
//
//    private void initializeStage(BaseController controller){
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(controller.getFxmlName() + ".fxml"));
//        fxmlLoader.setController(controller);
//
//
//        Parent parent;
//        try {
//            parent = fxmlLoader.load();
//        }catch (IOException e){
//            e.printStackTrace();
//            return;
//        }
//
//        Scene scene = new Scene(parent);
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.show();
//
//    }

