package core;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DisplayController implements ControlledScreen {
    @FXML
    Button displayButton;
    @FXML
    Label movieTitleLabel;

    // private static Scene searchScene;
    ScreensController myController;

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    /*static void setSearchScene(Scene scene) {
        searchScene = scene;
    }*/

    @FXML
    private void anotherMovieButton(ActionEvent event) {
      /* Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(searchScene);
        primaryStage.show();*/
      myController.setScreen(Main.searchScreenID);
    }

    void setMovieTitleLabel(String t) {
        movieTitleLabel.setText(t);
    }
}
