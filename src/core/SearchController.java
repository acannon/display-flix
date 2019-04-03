package core;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SearchController {
    Button searchButton;
    private static Scene displayScene;

    static void setDisplayScene(Scene scene) {
        displayScene = scene;
    }

    @FXML
    private void searchButton(ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(displayScene);
        primaryStage.show();
    }
}
