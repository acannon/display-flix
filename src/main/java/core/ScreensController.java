package core;

import com.sun.scenario.effect.Blend;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashMap;


public class ScreensController extends StackPane {
    // Holds the possible screens to be displayed

    private HashMap<String, Node> screens = new HashMap<>();
    private FXMLLoader loader;

    public  ScreensController() {
        super();
    }

    // Add a screen to the HashMap to make it possible to display
    public void addScreen(String name, Node screen) {
        screens.put(name, screen);
    }

    // Return the Node that is the screen based on the screen's name
    public Node getScreen(String name) {
        return screens.get(name);
    }

    // Load a specified fxml file (resource) based on a screen id
    // This does NOT add a screen to the Scene Graph, it places them in the HashMap for future use
    public boolean loadScreen(String name, String resource) {
        try {
            loader = new FXMLLoader((getClass().getResource(resource)));

            // once resource is loaded, we can get the controller from the Parent
            // NOTE: FXML is a tree structure, Parent is the "parent of the tree structure"
            Parent loadScreen = (Parent) loader.load();
            ControlledScreen myScreenController = ((ControlledScreen) loader.getController());

            /* inject this ScreenController so each screen knows how to get here, so the individual
             * screens can switch on their own
             */
            myScreenController.setScreenParent(this);

            // finally, add screen to HashMap
            addScreen(name, loadScreen);

            return true;
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    // THIS method displays a screen available in the HashMap
    public boolean setScreen(final String name) {
        if(screens.get(name) != null) {     // the requested screen exists in HashMap

            final DoubleProperty opacity = opacityProperty();

            if(!getChildren().isEmpty()) {  // if there is more than one screen
                Timeline fade = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity,1.0)),
                        new KeyFrame(new Duration(1000), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                getChildren().remove(0);    // remove previous screen
                                getChildren().add(0, screens.get(name));    // add new screen

                                // get DisplayController to set data on Display Screen
                                DisplayController controller = loader.<DisplayController>getController();
                                controller.setMovieInfo();

                                // resize screen, then fade in using Timeline
                                Main.resizeScreen();
                                Timeline fadeIn = new Timeline(
                                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                                        new KeyFrame(new Duration(500), new KeyValue(opacity, 1.0))
                                );
                                fadeIn.play();
                            }
                        }, new KeyValue(opacity, 0.0))
                );
                fade.play();
            }
            else {  // this is first screen, just display
                setOpacity(0.0);

                // take tree structure of specified screen and add to Screen Graph
                // "getting the children for Scene graph and then adding screens.get(name), which is specified screen
                getChildren().add(screens.get(name));

                // fade in screen animation
                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(2500),new KeyValue(opacity, 1.0)) // 2.5 seconds
                );
                fadeIn.play();
            }
            return true;
        }
        else {
            System.err.println("A screen with that name has not been loaded.");
            return false;
        }
    }

    // Remove a screen from the possible screens
    public boolean unloadScreen(String name) {
        if(screens.remove(name) == null) {
            System.err.println("Screen does not exist");
            return false;
        }
        else
            return true;
    }

}