package core;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SearchController implements ControlledScreen{
    @FXML
    Button searchButton;
    @FXML
    TextField searchBox;

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
        System.out.println(Model.theMovie.title);




        // switch to Display Scene
        /*Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(displayScene);
        primaryStage.show();*/
        myController.setScreen(Main.displayScreenID);
    }


}
