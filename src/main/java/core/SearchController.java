package core;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SearchController implements ControlledScreen{
    @FXML
    Button searchButton;
    @FXML
    TextField searchBox;
    @FXML
    Label searchErrorLabel;

    // private static Scene displayScene;
    ScreensController myController;

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    /*static void setDisplayScene(Scene scene) {
        displayScene = scene;
    } OLD APPROACH */

    @FXML
    private void searchButton(ActionEvent event) {
        // get entered text
        String searchTitle = searchBox.getText();

        // load in JSON file and get Java object
        Model model = new Model();
        Model.theMovie = model.getMovie(searchTitle);

        if(Model.theMovie == null) {
            searchErrorLabel.setVisible(true);
            PauseTransition visiblePause = new PauseTransition(
                    Duration.seconds(3)
            );
            visiblePause.setOnFinished(
                    displayLabel -> searchErrorLabel.setVisible(false)
            );
            visiblePause.play();
        }


        System.out.println(Model.theMovie.title);

        // switch to Display Scene
        /*Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(displayScene);
        primaryStage.show();*/
        myController.setScreen(Main.displayScreenID);
    }


}
