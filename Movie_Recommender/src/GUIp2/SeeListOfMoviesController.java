package GUIp2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SeeListOfMoviesController implements Initializable {

    @FXML
    Button AddMovies;
    @FXML
    Button PreviousRatings;
    @FXML
    Button BackButton;
    @FXML
    Button GetRecommendation1;
    @FXML
    AnchorPane SeeListMovies;

    public SeeListOfMoviesController() {
    }

    public void PreviousRatingsButton(ActionEvent actionEvent) throws IOException {
        Parent PreviousRatingsParent = (Parent) FXMLLoader.load(this.getClass().getResource("PreviousRatings.fxml"));
        this.SeeListMovies.getChildren().add(PreviousRatingsParent);
    }

    public void goToRecommendation(ActionEvent actionEvent) throws IOException {
        Parent PreviousRatingsParent = (Parent) FXMLLoader.load(this.getClass().getResource("GetRecommendation.fxml"));
        this.SeeListMovies.getChildren().add(PreviousRatingsParent);
    }

    public void addMoviesButton(ActionEvent actionEvent) throws IOException {
        Parent PreviousRatingsParent = (Parent) FXMLLoader.load(this.getClass().getResource("AddNewMovie.fxml"));
        this.SeeListMovies.getChildren().add(PreviousRatingsParent);
    }


    public void ListOfMoviesButton(ActionEvent actionEvent) throws IOException {
        Parent PreviousRatingsParent = (Parent) FXMLLoader.load(this.getClass().getResource("SeeListOfMovies.fxml"));
        this.SeeListMovies.getChildren().add(PreviousRatingsParent);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void BackToMenu(ActionEvent actionEvent) throws IOException {
        Parent LoginParent = (Parent) FXMLLoader.load(this.getClass().getResource("MenuOptions.fxml"));
        this.SeeListMovies.getChildren().add(LoginParent);
    }
}
