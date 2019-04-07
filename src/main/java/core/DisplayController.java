package core;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class DisplayController implements ControlledScreen {
    @FXML Button displayButton;
    @FXML Label movieTitleLabel;
    @FXML Label tagline;
    @FXML ScrollPane movieDescription;
    @FXML ImageView moviePoster;
    @FXML WebView movieTrailer;

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

    public void setMovieInfo() {
        // set movie poster
        try {
            URL posterURL = new URL("https://image.tmdb.org/t/p/original" + Model.theMovie.poster_path);
            BufferedImage temp = ImageIO.read(posterURL);
            Image posterImage = SwingFXUtils.toFXImage(temp, null);
            moviePoster.setImage(posterImage);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // set movie title label
        movieTitleLabel.setText(Model.theMovie.title);

        // set movie tagline label
        tagline.setWrapText(true);
        tagline.setText(Model.theMovie.tagline);

        // set description area
        Text description = new Text(Model.theMovie.overview);
        description.setFont(Font.font(14.0));
        description.setWrappingWidth(movieDescription.getPrefWidth());
        movieDescription.setContent(description);

        // get video
        String videoKey = Model.getFirstTrailer(Model.theMovie.id);
        String videoURL = "https://www.youtube.com/embed/" + videoKey;
        // movieTrailer = new WebView();
        movieTrailer.getEngine().load(videoURL);

    }

    @FXML
    void controlPlay(ActionEvent event) {
        System.out.println("You clicked the media thing");
    }
}
