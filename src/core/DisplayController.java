package core;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DisplayController {
    @FXML
    Button displayButton;
    private static Scene searchScene;

    static void setSearchScene(Scene scene) {
        searchScene = scene;
    }

    @FXML
    private void displayButton(ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(searchScene);
        primaryStage.show();
    }
}
