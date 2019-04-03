package core;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {

    Parent search, display;
    Scene searchScene, displayScene;

    @FXML
    public Button searchButton;

    @Override
    public void start(Stage primaryStage) throws Exception{
        search = FXMLLoader.load(getClass().getResource("Search.fxml"));
        display = FXMLLoader.load(getClass().getResource("Display.fxml"));

        searchScene = new Scene(search, 500, 65);
        displayScene = new Scene(display, 600, 400);
        SearchController.setDisplayScene(displayScene);
        DisplayController.setSearchScene(searchScene);


        primaryStage.setTitle("Display Flix");
        primaryStage.setScene(searchScene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
