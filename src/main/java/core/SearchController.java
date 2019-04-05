package core;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SearchController {
    @FXML
    Button searchButton;
    @FXML
    TextField searchBox;

    private static Scene displayScene;

    static void setDisplayScene(Scene scene) {
        displayScene = scene;
    }

    @FXML
    private void searchButton(ActionEvent event) {
        // get entered text
        String searchTitle = searchBox.getText();
        System.out.println(searchTitle);

        // load in JSON file


        // switch to Display Scene
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(displayScene);
        primaryStage.show();
    }
}
