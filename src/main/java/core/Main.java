package core;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static String searchScreenID = "search";
    public static String searchScreenFile = "/Search.fxml";
    public static String displayScreenID = "display";
    public static String displayScreenFile = "/Display.fxml";

    private static Stage primaryStage;

    public static void resizeScreen(){
        primaryStage.sizeToScene();
        primaryStage.centerOnScreen();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;

        // create ScreensController and load possible screens
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(Main.searchScreenID, Main.searchScreenFile);   // name, resource
        mainContainer.loadScreen(Main.displayScreenID, Main.displayScreenFile);

        mainContainer.setScreen(Main.searchScreenID);

        // load in fxml files - NOW this is done in ScreensController.loadScreen
        // Parent search = FXMLLoader.load(getClass().getResource("/Search.fxml"));
        // Parent display = FXMLLoader.load(getClass().getResource("/Display.fxml"));

        // create scenes - NOW this is done differently
        // Scene searchScene = new Scene(search, 500, 65);
        // Scene displayScene = new Scene(display, 600, 400);

        // tell scenes about each other - UNNECESSARY, was a different approach
        // SearchController.setDisplayScene(displayScene);
        // DisplayController.setSearchScene(searchScene);

        // show Application starting with search box
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        primaryStage.setTitle("Display Flix");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
